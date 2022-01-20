package spring.security.jwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import spring.security.jwt.service.InMemoryUserDetailsService;

@Component
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    InMemoryUserDetailsService inMemoryUserDetailsService;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Autowired
    JwtAuthenticationEntrypoint jwtAuthenticationEntrypoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inMemoryUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/api/authenticate",
                            "/v2/api-docs",
                            "/configuration/ui",
                            "/swagger-resources/**",
                            "/configuration/security",
                            "/swagger-ui.html",
                            "/swagger-ui/",
                            "/webjars/**")
                    .permitAll()
                    .anyRequest().authenticated()
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(jwtAuthenticationEntrypoint)
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
