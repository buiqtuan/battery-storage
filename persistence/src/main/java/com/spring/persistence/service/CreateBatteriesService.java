package com.spring.persistence.service;

import com.spring.common.model.Battery;
import com.spring.persistence.entity.BatteryEntity;
import com.spring.persistence.repository.BatteryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateBatteriesService {

    private final BatteryRepository batteryRepository;

    public CreateBatteriesService(BatteryRepository batteryRepository) {
        this.batteryRepository = batteryRepository;
    }

    public void createBattery(Battery battery) {

        var entity = BatteryEntity.builder()
                .name(battery.getName())
                .postCode(battery.getPostCode())
                .wattCapacity(battery.getWattCapacity())
                .build();

        this.batteryRepository.save(entity);
    }

    public void createBatteries(List<Battery> batteries) {
        var entities = batteries.stream().map(b -> new BatteryEntity(null, b.getName(), b.getPostCode(), b.getWattCapacity())).collect(Collectors.toList());

        this.batteryRepository.saveAll(entities);
    }
}
