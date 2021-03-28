package com.java.multifactor.api;

import com.java.multifactor.entity.Customer;
import com.java.multifactor.entity.LogginUser;
import com.java.multifactor.entity.PostLoginUser;
import com.java.multifactor.entity.RegRequestedUser;
import com.java.multifactor.repository.CustomerRepository;
import com.java.multifactor.service.CustomerServices;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

@RestController
public class HomeController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private CustomerServices customerService;

    @GetMapping(value = "/api/home", produces = MediaType.APPLICATION_JSON_VALUE)
    public String home() {
        return "2 step authentication worked";
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String login() {
        return "login";
    }

    @GetMapping(value = "/api/signup/new")
    public String registrationPage(Model model) {
        return "registration";
    }

    @PostMapping(value = "/api/signup/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public String register(@RequestBody RegRequestedUser regRequestedUser) {
        if (null != regRequestedUser) {
            Customer cust = new Customer();
            cust.setEmail(regRequestedUser.getEmail());
            cust.setfName(regRequestedUser.getfName());
            cust.setlName(regRequestedUser.getlName());
            cust.setPassword(regRequestedUser.getPassword());
            cust.setEnabled(regRequestedUser.isEnabled());
            cust.setOneTimePassword(null);
            cust.setOtpRequestedTime(null);

            customerRepository.save(cust);
            return "login";
        }
        return "failure";
    }



    @PostMapping(value = "/api/login/twofa", produces = MediaType.APPLICATION_JSON_VALUE)
    public String validateLogin(@RequestBody LogginUser loginUser, Model model) {
        Customer customer = customerRepository.findCustomerByEmail(loginUser.getUserName());

        PostLoginUser postLoginUser = new PostLoginUser(customer.getEmail(), customer.isEnabled());
        postLoginUser.setfName(customer.getfName());
        postLoginUser.setlName(customer.getlName());
        postLoginUser.setLoggedInTime(null);

        model.addAttribute("loggedInUser", postLoginUser);
        if (is2fcAuthRequired(customer)) {
            try {
                customerService.generateOneTimePassword(customer);
                // throw new InsufficientAuthenticationException("OTP");
            } catch (UnsupportedEncodingException | MessagingException e) {
                throw new AuthenticationServiceException(
                        "Error while sending OTP email.");
            }
        }
        return "2faPage";
    }

    @PostMapping(value = "/api/login/validate", produces = MediaType.APPLICATION_JSON_VALUE)
    public String validate2fa(@RequestBody PostLoginUser loggedInUser, Model model, HttpServletRequest request) {
        if(null==loggedInUser && StringUtils.isBlank(loggedInUser.getOtp())){
            return "login";
        }
        String otp = loggedInUser.getOtp();
        Customer loggedInCust = customerRepository.findCustomerByEmail(loggedInUser.getEmail());

        model.addAttribute("loggedInUser", loggedInUser);
        loggedInUser.setOtp(null);
        loggedInUser.setLoggedInTime(new Timestamp(System.currentTimeMillis()));

        if(!verify2fa(otp, loggedInCust)){
            model.addAttribute("warning-msg", "OTP was expired");
            return "login";
        }

        generateAuthToken(request);

        return "home";
    }

    private void generateAuthToken(HttpServletRequest request) {
        String token = UUID.randomUUID().toString();
        request.setAttribute("authToken", token);
    }

    private boolean verify2fa(String otp, Customer loggedInCust) {
        boolean isEqueal = StringUtils.compare(otp, loggedInCust.getOneTimePassword()) == 0;
        if(isEqueal && loggedInCust.isOTPRequired()){
            return true;
        }
        customerService.clearOTP(loggedInCust);
        return false;
    }

    private boolean is2fcAuthRequired(Customer loginUser) {
        if (null != loginUser && !loginUser.isOTPRequired()) {
            customerService.clearOTP(loginUser);
            return false;
        }
        return true;
    }
}
