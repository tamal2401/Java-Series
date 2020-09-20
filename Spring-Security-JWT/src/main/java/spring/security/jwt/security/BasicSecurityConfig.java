package spring.security.jwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Configuration
@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .authorizeRequests()
                .antMatchers("/", "/api/v1/index", "css/*", "js/*").permitAll()
                .antMatchers("/api/student/**").hasRole(UserRoles.STUDENT.name())
                .antMatchers("/api/admin/**").hasRole(UserRoles.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails tamalUser = User.builder()
                .username("tamal")
                .password(passwordEncoder.encode("password"))
                .roles(UserRoles.ADMIN.name())
                .build();

        UserDetails sasaDetais = User.builder()
                .username("susanta")
                .password(passwordEncoder.encode("password"))
                .roles(UserRoles.STUDENT.name())
                .build();

        return new InMemoryUserDetailsManager(tamalUser, sasaDetais);
    }

    @Bean
    PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(10);
    }
}
