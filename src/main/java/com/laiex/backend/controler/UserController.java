package com.laiex.backend.controler;

import com.laiex.backend.model.RegisterBody;
import com.laiex.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.laiex.backend.exception.ErrorResponse;

import java.sql.SQLIntegrityConstraintViolationException;

@RestController
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/register")
//    @ResponseStatus(value = HttpStatus.OK)
//    public void register(@RequestBody RegisterBody body) throws SQLIntegrityConstraintViolationException {
//        //System.out.println("/register request is received");
//        userService.register(body.username(), body.password(), body.firstName(), body.lastName(), body.phoneNumber());
//    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterBody body) {
        try{
            userService.register(body.username(), body.password(), body.firstName(), body.lastName(), body.phoneNumber());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
                return ResponseEntity.badRequest().body(new ErrorResponse("This username is already taken. Please choose another one."));
        }
    }

}

