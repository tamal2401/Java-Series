package com.spring.jpa;

import com.spring.jpa.domain.UserProfile;
import com.spring.jpa.domain.User;
import com.spring.jpa.repository.UserRepo;
import com.spring.jpa.repository.UserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class InitializeData {

    private final UserRepo repo;

    private final UserProfileRepo userProfileRepo;

    @Autowired
    @Qualifier("CustomTemplate")
    private JdbcTemplate template;

    @Autowired
    public InitializeData(UserRepo repo, UserProfileRepo userProfileRepo) {
        this.repo = repo;
        this.userProfileRepo = userProfileRepo;
    }

    @PostConstruct
    public void initialize() throws SQLException {
        User user1 = new User();
        user1.setUserId(1);
        user1.setEmail("das.tamal00496@gmail.com");
        user1.setFName("Tamal");
        user1.setLName("Das");
        user1.setOrgName("Deloitte");
        user1.setPhnNo("7003192645");

        User user2 = new User();
        user2.setUserId(2);
        user2.setEmail("onlyjohn98.com");
        user2.setFName("John");
        user2.setLName("Paul");
        user2.setOrgName("TCS");
        user2.setPhnNo("8910673912");

        repo.saveAll(Arrays.asList(user1, user2));

        UserProfile profile1 = new UserProfile();
        profile1.setUpId(1);
        profile1.setCurrentJobTitle("Developer");
        profile1.setEmailId("abc@Gmail.com");
        profile1.setIndustry("Retail");
        profile1.setJobCategoryId(1);
        profile1.setJobClassId(1);
        profile1.setJobFunctionId(1);
        profile1.setPrimarySkillId(1);
        profile1.setProjectOpted("deloitte");
        profile1.setRoleId(1);

        userProfileRepo.save(profile1);

        List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());
        splitUpNames.forEach(name -> System.out.println(String.format("Inserting customer record for %s %s", name[0], name[1])));
        template.execute("DROP TABLE customers IF EXISTS");
        template.execute("CREATE TABLE customers(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

        Connection conn = template.getDataSource().getConnection();
        conn.setAutoCommit(false);
        try{
            template.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);
            //conn.commit();
            List<Map<String, Object>> result = template.queryForList("SELECT * FROM CUSTOMERS");
            System.out.println(result);
        }catch(DataAccessException e){
            System.out.println(e.getCause());
            conn.rollback();
        }finally {
            if(conn.isClosed())conn.close();
        }
    }
}
