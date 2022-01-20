package spring.security.jwt.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import spring.security.jwt.service.InMemoryUserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    InMemoryUserDetailsService inMemoryUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String tokenHeader = request.getHeader("Authorization");
        String userName = null;
        String token = null;

        if(tokenHeader!=null && tokenHeader.startsWith("Bearer ")){
            try{
                token = tokenHeader.substring(7);
                userName = jwtTokenUtil.getUserNameFromToken(token);
            }catch(ExpiredJwtException ex){
                System.out.println("JWT Token has expired");
            }catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            }
            userName = jwtTokenUtil.getUserNameFromToken(token);
        }

        if(token!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails details = inMemoryUserDetailsService.loadUserByUsername(userName);
            if(jwtTokenUtil.validateToken(token, details)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        details, null, details.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
