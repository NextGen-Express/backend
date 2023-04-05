package com.laiex.backend.service;

import com.laiex.backend.db.OrderRepository;
import com.laiex.backend.db.UserRepository;
import com.laiex.backend.db.entity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;


@Service
public class UserService {
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    // UserService constructor
    public UserService(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder, UserRepository userRepository, OrderRepository orderRepository) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<String> register(String username, String password, String firstName, String lastName, String phoneNumber) {
        // Check if the username already exists in the database
        if(userRepository.existsByUsername(username)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this email already exists");
        }
        // userRepository.findIdByUsername(username);
        UserDetails user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build();
        userDetailsManager.createUser(user);
        userRepository.fillOutInfoByUsername(username, firstName, lastName, phoneNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body("User with this email successfully created");
    }

    // newly added need comments
    public Long findUserIdByUsername(String username) {
        return userRepository.findIdByUsername(username);
    }
}
