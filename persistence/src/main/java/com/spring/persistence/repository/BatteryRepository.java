package com.spring.persistence.repository;

import com.spring.persistence.entity.BatteryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatteryRepository extends CrudRepository<BatteryEntity, Long> {

    List<BatteryEntity> findByPostCodeBetweenOrderByNameDesc(Long from, Long to);
}
