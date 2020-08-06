package com.java.stalker.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class AuthResponse {
    public String user;
    public String email;
    public String token;
}
