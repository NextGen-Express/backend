package com.laiex.backend.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.annotation.Order;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public record UserEntity(
        @Id Long id,
        String username,
        String password,
        @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") String lastName,
        @JsonProperty("phone_number") Integer phoneNumber

) {
    //get this user id
    public long getId() {
        return id;
    }
}
