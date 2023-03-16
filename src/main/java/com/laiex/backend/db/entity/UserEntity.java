package com.laiex.backend.db.entity;

import org.springframework.data.annotation.Id;

public record UserEntity(
        @Id Long id,
        String username,
        String password,
        String first_name,
        String last_name,
        Integer phone_number
) {
}
