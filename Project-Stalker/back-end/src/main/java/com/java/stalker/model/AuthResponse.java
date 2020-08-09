package com.java.stalker.model;

import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {
    public String user;
    public String email;
}
