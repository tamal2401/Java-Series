package com.java.multifactor.service;

import com.java.multifactor.entity.Customer;
import com.java.multifactor.repository.CountryRepository;
import com.java.multifactor.repository.CustomerRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    CountryRepository countryRepo;

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    PasswordEncoder passwordEncoder;


    public void generateOneTimePassword(Customer customer)
            throws UnsupportedEncodingException, MessagingException {
        String OTP = RandomString.make(8);
        String encodedOTP = passwordEncoder.encode(OTP);

        customer.setOneTimePassword(encodedOTP);
        customer.setOtpRequestedTime(new Date());

        customerRepo.save(customer);

        sendOTPEmail(customer, OTP);
    }

    public void sendOTPEmail(Customer customer, String OTP)
            throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@shopme.com", "Shopme Support");
        helper.setTo(customer.getEmail());

        String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";

        String content = "<p>Hello " + customer.getfName() + "</p>"
                + "<p>For security reason, you're required to use the following "
                + "One Time Password to login:</p>"
                + "<p><b>" + OTP + "</b></p>"
                + "<br>"
                + "<p>Note: this OTP is set to expire in 5 minutes.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
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
