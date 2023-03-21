package com.laiex.backend.db;

import com.laiex.backend.db.entity.UserEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<UserEntity, Long> {

    @Modifying
    @Query("UPDATE users SET first_name = :firstName, last_name = :lastName, phone_number = :phoneNumber")
    void fillOutInfoByUsername(String username, String firstName, String lastName, Integer phoneNumber);
}
