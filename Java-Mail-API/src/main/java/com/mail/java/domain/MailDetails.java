package com.mail.java.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class MailDetails {
    private String name;
    private String email;
    private String sub;
    private String msg;
}
