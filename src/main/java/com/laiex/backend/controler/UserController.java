package com.laiex.backend.controler;

import com.laiex.backend.model.requestbody.RegisterBody;
import com.laiex.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterBody body) {
        try{
            userService.register(body.username(), body.password(), body.firstName(), body.lastName(), body.phoneNumber());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}

