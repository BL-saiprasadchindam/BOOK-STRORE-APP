package com.chindam.USER_REGISTRATION.binding;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginForm {
    private String email;
    private String password;
}
