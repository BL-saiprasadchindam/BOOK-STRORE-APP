package com.chindam.USER_REGISTRATION.binding;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RegisterForm {
    private String fullName;
    private String password;
    private String email;
    private Long phoneNumber;
}
