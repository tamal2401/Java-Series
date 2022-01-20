package spring.security.jwt.model;

import lombok.Data;

@Data
public class TokenRequest {
    private String userName;
    private String password;
}
