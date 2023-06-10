package com.chindam.USER_REGISTRATION.binding;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseData {
    private String message;
    private String userId;
}
