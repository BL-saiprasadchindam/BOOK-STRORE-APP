package com.chindam.USER_REGISTRATION.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@Component
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String userId;
    private String fullName;
    private String password;
    private String email;
    private Long phoneNumber;

}
