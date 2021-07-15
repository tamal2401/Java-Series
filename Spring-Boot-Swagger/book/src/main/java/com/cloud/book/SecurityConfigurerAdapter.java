package com.cloud.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@PropertySource("classpath:auth.properties")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Value("${mesh.security.auth.username}")
    private String userName;
    @Value("${mesh.security.auth.password}")
    private String pwd;
    @Value("${mesh.security.auth.role}")
    private String role;
    @Value("${security.swagger.enabled: false}")
    public boolean swaggerFlag;
    @Autowired
    CustomBasicAuthEntryPoint customBasicAuthEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (swaggerFlag) {
            http.authorizeRequests().antMatchers("/v2/api-docs",
                    "/configuration/ui",
                    "/swagger-resources/**",
                    "/configuration/security",
                    "/swagger-ui.html",
                    "/swagger-ui/",
                    "/webjars/**").permitAll();
        }

        HttpBasicConfigurer<HttpSecurity> configurer = http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        if(swaggerFlag){
            configurer.authenticationEntryPoint(customBasicAuthEntryPoint);
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.userDetailsService(userDetailsService()).passwordEncoder(encoder);
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        final User user = new User(
                this.userName,
                this.pwd,
                AuthorityUtils.commaSeparatedStringToAuthorityList(this.role));
        return new InMemoryUserDetailsManager(user);
    }
}

