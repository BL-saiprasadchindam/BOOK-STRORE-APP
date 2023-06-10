package com.chindam.USER_REGISTRATION.controller;

import com.chindam.USER_REGISTRATION.binding.LoginForm;
import com.chindam.USER_REGISTRATION.binding.RegisterForm;
import com.chindam.USER_REGISTRATION.binding.ResponseData;
import com.chindam.USER_REGISTRATION.exception.InvalidUserNameAndPassword;
import com.chindam.USER_REGISTRATION.exception.UserAlreadyExistWithEmail;
import com.chindam.USER_REGISTRATION.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.*;

@RestController("/api/user")
public class UserRegistrationController {

    List<User> users = new ArrayList<>();

    @PostConstruct
    private void init() {
        users.add(new User(UUID.randomUUID().toString(),"saiprasad","123456","saiprasad@gmail.com",8080707195l));
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody RegisterForm formData) {
        Optional<User> userOptional = users.stream()
                .filter(user1 -> user1.getEmail().equals(formData.getEmail()))
                .findAny();

        if(userOptional.isPresent()) {
            return new ResponseEntity<>("Email Already Exist", HttpStatus.OK);
        }

        User user = new User();
        BeanUtils.copyProperties(formData,user);
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        users.add(user);
        return new ResponseEntity<>("User Registered Successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData> login(@RequestBody LoginForm data) {

        Optional<User> optionalUser = users.stream()
                .filter(user -> user.getEmail().equals(data.getEmail()) & user.getPassword().equals(data.getPassword()))
                .findFirst();

        if(optionalUser.isPresent()) {
            return new ResponseEntity<>(new ResponseData("Login Successfully",optionalUser.get().getUserId()), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseData("Invalid Email and Password",null), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
