package com.laiex.backend.db;

import com.laiex.backend.db.entity.UserEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserRepository extends ListCrudRepository<UserEntity, Long> {

    @Modifying
<<<<<<< HEAD
    @Query("UPDATE users SET first_name = :firstName, last_name = :lastName, phone_number = :phoneNumber")
    void fillOutInfoByUsername(String username, String firstName, String lastName, Integer phoneNumber);
    // newly added need comment
=======
    @Query("UPDATE users SET first_name = :firstName, last_name = :lastName, phone_number = :phoneNumber WHERE username = :username")
    void fillOutInfoByUsername(String username, String firstName, String lastName, String phoneNumber);

    @Query("SELECT id FROM users WHERE username = :username")
    Integer findIdByUsername(String username);

>>>>>>> 5801ecdd4e2f07842093c8b7c62b326c78ffb95a
    UserEntity findByUsername(String username);
}
