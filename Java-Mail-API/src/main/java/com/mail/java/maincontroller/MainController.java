package com.mail.java.maincontroller;

import com.mail.java.MailApiApplication;
import com.mail.java.domain.MailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class MainController {

    @Autowired
    MailSender sender;

    @PostMapping(value = "/send/mail",
                consumes = "application/json",
                produces = "application/json")
    public void sendMail(@RequestBody MailDetails details, HttpServletResponse response) {
        try {
            String name = details.getName().replaceAll("\\s", "_");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(name);
            message.setTo("to whom the mail will be sent");
            message.setSubject("Mail from <" + details.getEmail() + "> : " + details.getSub());
            message.setText(details.getMsg());
            sender.send(message);
        } catch (Exception e) {
            System.out.println("Error occured while sending mail to: " + details.getEmail());
            System.out.println("Error ; " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Mail service not responding", e);
        }
    }

}
