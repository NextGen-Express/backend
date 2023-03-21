package com.laiex.backend.controler;

import com.laiex.backend.model.RegisterBody;
import com.laiex.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.OK)
    public void register(@RequestBody RegisterBody body) {
        System.out.println("/register request is received");
        userService.register(body.username(), body.password(), body.firstName(), body.lastName(), body.phoneNumber());
    }
}
