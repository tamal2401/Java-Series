package com.spring.jpa;

import com.spring.jpa.domain.User;
import com.spring.jpa.domain.UserProfile;
import com.spring.jpa.repository.UserProfileRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.invoke.MethodHandles;

@SpringBootApplication
public class SpringMultipledatasourceApplication implements CommandLineRunner {

	private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    UserProfileRepo userProfileRepo;

    @Autowired
    JdbcTemplate template;

    public static void main(String[] args) {
        SpringApplication.run(SpringMultipledatasourceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // USing spring data source
        UserProfile profile = new UserProfile(
                1, "das.tamal00496@gmail.com", "SSE"
        );
        userProfileRepo.save(profile);

        // Using spring jdbc template
        User user = new User(
                1, "Tamal", "Das"
        );

        try{
        	int response = template.update("INSERT into USER (User_Id, First_Name, Last_Name) VALUES (?, ?, ?)",
					user.getUserId(),
					user.getFName(), user.getLName());
		}catch(DataAccessException ex){
        	log.info("Exception occured while saving data: {}", ex.getMessage());
		}
    }
}
