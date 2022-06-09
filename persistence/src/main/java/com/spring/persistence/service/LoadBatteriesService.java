package com.spring.persistence.service;

import com.spring.common.model.Battery;
import com.spring.persistence.repository.BatteryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoadBatteriesService {

    private final BatteryRepository batteryRepository;

    public LoadBatteriesService(BatteryRepository batteryRepository) {
        this.batteryRepository = batteryRepository;
    }

    public List<Battery> byPostCodeRange(Long from, Long to) {

        return this.batteryRepository
                    .findByPostCodeBetweenOrderByNameDesc(from, to)
                    .stream().map(b -> new Battery(b.getName(), b.getPostCode(), b.getWattCapacity())).collect(Collectors.toList());
    }
}
