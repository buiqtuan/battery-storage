package com.spring.batteries.adapter;

import com.spring.batteries.usecase.CreateBatteriesUseCase;
import com.spring.common.model.Battery;
import com.spring.persistence.service.CreateBatteriesService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class CreateBatteryAdapter implements CreateBatteriesUseCase {

    private final CreateBatteriesService createBatteriesService;

    public CreateBatteryAdapter(CreateBatteriesService createBatteriesService) {
        this.createBatteriesService = createBatteriesService;
    }

    @Override
    public void createSingle(Battery battery) {

        try {
            this.createBatteriesService.createBattery(battery);
        } catch (Exception e) {
            log.error("CreateBatteryAdapter.createSingle", e.getMessage());

            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void createList(List<Battery> batteries) {

        try {
            this.createBatteriesService.createBatteries(batteries);
        } catch (Exception e) {
            log.error("CreateBatteryAdapter.createList", e.getMessage());

            throw new RuntimeException(e.getMessage());
        }
    }
}
