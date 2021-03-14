package com.java.multifactor.api;

import com.java.multifactor.entity.Customer;
import com.java.multifactor.entity.User;
import com.java.multifactor.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping(value = "/api/home", produces = MediaType.APPLICATION_JSON_VALUE)
    public String home() {
        return "@ step authentication worked";
    }

    @PostMapping(value = "/api/register/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public String register(User user) {
        if(null!=user){
            Customer cust = new Customer();
            cust.setEmail(user.getEmail());
            cust.setfName(user.getfName());
            cust.setlName(user.getlName());
            cust.setPassword(user.getPassword());
            cust.setEnabled(user.isEnabled());
            cust.setOneTimePassword(null);
            cust.setOtpRequestedTime(null);

            customerRepository.save(cust);
            return "Success";
        }
        return "Failure";
    }

    @GetMapping(value = "/api/user/new")
    public String registrationPage(){
        return "registration";
    }
}
