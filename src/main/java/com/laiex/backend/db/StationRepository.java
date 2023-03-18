package com.laiex.backend.db;

import com.laiex.backend.db.entity.StationEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface StationRepository extends ListCrudRepository<StationEntity, Long> {
}
