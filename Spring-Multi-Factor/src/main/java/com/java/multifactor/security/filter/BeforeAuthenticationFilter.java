package com.java.multifactor.security.filter;

import com.java.multifactor.entity.Customer;
import com.java.multifactor.service.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class BeforeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private CustomerServices customerService;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authManager) {
        super.setAuthenticationManager(authManager);
    }

    @Autowired
    @Override
    public void setAuthenticationFailureHandler(
            AuthenticationFailureHandler failureHandler) {
        super.setAuthenticationFailureHandler(failureHandler);
    }

    @Autowired
    @Override
    public void setAuthenticationSuccessHandler(
            AuthenticationSuccessHandler successHandler) {
        super.setAuthenticationSuccessHandler(successHandler);
    }

    public BeforeAuthenticationFilter() {
        setUsernameParameter("email");
        super.setRequiresAuthenticationRequestMatcher(
                new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String uName = request.getParameter("email");

        Customer customer = customerService.getCustomerByEMail(uName);

        if(null!=customer){
            if (!customer.isOTPRequired()) {
                return super.attemptAuthentication(request, response);
            }

            try {
                customerService.generateOneTimePassword(customer);
                throw new InsufficientAuthenticationException("OTP");
            } catch (UnsupportedEncodingException|MessagingException e) {
                throw new AuthenticationServiceException(
                        "Error while sending OTP email.");
            }
        }
        return super.attemptAuthentication(request, response);
    }
}
