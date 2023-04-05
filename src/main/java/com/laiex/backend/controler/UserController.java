package com.laiex.backend.controler;

import com.laiex.backend.model.requestbody.RegisterBody;
import com.laiex.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterBody body) {
        return userService.register(body.username(), body.password(), body.firstName(), body.lastName(), body.phoneNumber());
    }
}

