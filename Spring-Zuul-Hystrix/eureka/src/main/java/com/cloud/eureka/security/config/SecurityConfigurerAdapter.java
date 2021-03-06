package com.cloud.eureka.security.config;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.ClientFilter;
import de.codecentric.boot.admin.client.config.ClientProperties;
import de.codecentric.boot.admin.client.config.InstanceProperties;
import de.codecentric.boot.admin.client.config.SpringBootAdminClientAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.*;

@Configuration
@PropertySource("classpath:auth.properties")
public class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Value("${mesh.security.auth.username}")
    private String userName;
    @Value("${mesh.security.auth.password}")
    private String pwd;
    @Value("${mesh.security.auth.role}")
    private String role;
    @Value("${mesh.security.auth.client.pwd}")
    private String clientPwd;

    private final ApplicationInfoManager aim;

    private ClientProperties configuration;
    private InstanceProperties adminProp;

    public SecurityConfigurerAdapter(ApplicationInfoManager aim,
                                     ClientProperties configuration,
                                     InstanceProperties props) {
        this.aim = aim;
        this.configuration = configuration;
        this.adminProp = props;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.userDetailsService(userDetailsService()).passwordEncoder(encoder);
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        final User user = new User(this.userName, this.pwd, AuthorityUtils.commaSeparatedStringToAuthorityList(this.role));
        return new InMemoryUserDetailsManager(user);
    }

    @Bean("restTemplate")
    @Primary
    public RestTemplate getNonLoadBlancedTemplate(){
        return getTemplateWithInterceptor();
    }

    @Bean("loadBalancedRestTemplate")
    @LoadBalanced
    public RestTemplate getLoadBalancedTemplate(){
        return getTemplateWithInterceptor();
    }

    public RestTemplate getTemplateWithInterceptor(){
        RestTemplate template = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = template.getInterceptors();
        interceptors.add(new BasicInterceptor(this.userName, this.pwd));
        template.setInterceptors(interceptors);
        return template;
    }

    @PostConstruct
    public void setMetadata(){
        configuration.setPassword("admin");
        configuration.setUsername(new String(Base64.getDecoder().decode(this.clientPwd)));

        Map<String, String> adminMetaDataMap = new HashMap<>();
        adminMetaDataMap.put("user.name", this.userName);
        adminMetaDataMap.put("user.password", new String(Base64.getDecoder().decode(this.clientPwd)));
        adminProp.getMetadata().putAll(adminMetaDataMap);
    }
}

class BasicInterceptor implements ClientHttpRequestInterceptor{

    private String userName;
    private String pwd;

    public BasicInterceptor(String userName, String pwd) {
        this.userName = userName;
        this.pwd = pwd;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN));
        headers.add("Authorization", "Basic "+new String(Base64.getEncoder()
                .encode((this.userName+":"+new String(Base64.getDecoder().decode(this.pwd))).getBytes())));
        return execution.execute(request, body);
    }
}


