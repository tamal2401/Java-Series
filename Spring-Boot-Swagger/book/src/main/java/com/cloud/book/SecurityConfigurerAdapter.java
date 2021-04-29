package com.cloud.book;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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
}

