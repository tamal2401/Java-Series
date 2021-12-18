package spring.security.jwt.model;

import lombok.Data;

import java.util.List;

@Data
public class ApplicationUser {
    private String userName;
    private String pwd;
    private List<String> roles;
}
