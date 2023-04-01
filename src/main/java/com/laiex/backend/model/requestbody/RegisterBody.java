package com.laiex.backend.model.requestbody;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public record RegisterBody(
        String username,
        String password,
        @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") String lastName,
        @JsonProperty("phone_number") String phoneNumber
) {

}
