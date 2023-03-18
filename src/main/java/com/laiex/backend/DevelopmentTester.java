package com.laiex.backend;

import com.laiex.backend.db.UserRepository;
import com.laiex.backend.db.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DevelopmentTester implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(DevelopmentTester.class);
    private UserRepository userRepository;

    public DevelopmentTester(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserEntity user1 = new UserEntity(null,"a@gmail.com","123", "a","z",123);
        userRepository.save(user1);
    }
}
