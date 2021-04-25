package com.cloud.book;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.discovery.DiscoveryClient;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.ClientFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
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

    private ApplicationInfoManager aim;

    public SecurityConfigurerAdapter(ApplicationInfoManager aim) {
        this.aim = aim;
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

    /**
     * To set current service authdetails in discovery server for other services to read for management endpoints
     */
    @PostConstruct
    public void setMetadata(){
        Map<String, String> authMap = new HashMap<>();
        authMap.put("user.name", this.userName);
        authMap.put("user.password", new String(Base64.getDecoder().decode(this.clientPwd)));
        aim.getInfo().getMetadata().putAll(authMap);
    }

    /**
     * To set Basic Auth header in the outgoing request to eureka server from this service to register itself
     * Credentials are auth metadata for eureka server
     */
    @Bean
    public DiscoveryClient.DiscoveryClientOptionalArgs setDiscoverHeaderArgs(){
        DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs = new DiscoveryClient.DiscoveryClientOptionalArgs();
        discoveryClientOptionalArgs.setAdditionalFilters(Collections.singletonList(new IpClientFilter(this.userName, this.clientPwd)));
        return discoveryClientOptionalArgs;
    }
}

class BasicInterceptor implements ClientHttpRequestInterceptor{

    private final String userName;
    private final String pwd;

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

class IpClientFilter extends ClientFilter{

    private final String userName;
    private final String pwd;

    public IpClientFilter(String userName, String pwd) {
        this.userName = userName;
        this.pwd = pwd;
    }

    @Override
    public ClientResponse handle(ClientRequest cr) throws ClientHandlerException {
        MultivaluedMap<String, Object> headers = cr.getHeaders();
        headers.add("Authorization", "Basic "+new String(Base64.getEncoder()
                .encode((this.userName+":"+new String(Base64.getDecoder().decode(this.pwd))).getBytes())));
        return this.getNext().handle(cr);
    }
}


