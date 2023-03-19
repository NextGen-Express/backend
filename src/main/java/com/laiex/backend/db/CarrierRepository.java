package com.laiex.backend.db;

import com.laiex.backend.db.entity.CarrierEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface CarrierRepository extends ListCrudRepository<CarrierEntity, Long> {
}
