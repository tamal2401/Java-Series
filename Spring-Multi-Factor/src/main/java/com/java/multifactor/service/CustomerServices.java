package com.java.multifactor.service;

import com.java.multifactor.entity.Customer;
import com.java.multifactor.repository.CustomerRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
@Transactional
public class CustomerServices {

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    MailSender mailSender;

    @Autowired
    PasswordEncoder passwordEncoder;


    public void generateOneTimePassword(Customer customer)
            throws UnsupportedEncodingException, MessagingException {
        String OTP = RandomString.make(8);
        //String encodedOTP = passwordEncoder.encode(OTP);

        customer.setOneTimePassword(OTP);
        customer.setOtpRequestedTime(new Date());

        customerRepo.save(customer);

        sendOTPEmail(customer, OTP);
    }

    public void sendOTPEmail(Customer customer, String OTP)
            throws UnsupportedEncodingException, MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("contact@shopme.com");
        message.setTo(customer.getEmail());

        String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";

        String content = "<p>Hello " + customer.getfName() + "</p>"
                + "<p>For security reason, you're required to use the following "
                + "One Time Password to login:</p>"
                + "<p><b>" + OTP + "</b></p>"
                + "<br>"
                + "<p>Note: this OTP is set to expire in 5 minutes.</p>";

        message.setSubject(subject);

        message.setText(content);

        try {
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println("Error occured while sending mail to: " + customer.getEmail());
            System.out.println("Error ; " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Mail service not responding", e);
        }
    }

    public void clearOTP(Customer customer) {
        customer.setOneTimePassword(null);
        customer.setOtpRequestedTime(null);
        customerRepo.save(customer);
    }

    public Customer getCustomerByEMail(String email) {
        return customerRepo.findCustomerByEmail(email);
    }
}
