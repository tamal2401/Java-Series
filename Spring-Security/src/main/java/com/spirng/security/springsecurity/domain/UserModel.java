package com.spirng.security.springsecurity.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserModel {

    private String userName;
    private String pwd;
    private String role;
}
