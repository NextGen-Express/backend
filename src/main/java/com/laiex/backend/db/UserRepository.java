package com.laiex.backend.db;

import com.laiex.backend.db.entity.UserEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<UserEntity, Long> {

}
