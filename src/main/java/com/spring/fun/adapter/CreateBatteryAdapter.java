package com.spring.fun.adapter;

import com.spring.fun.entity.Battery;
import com.spring.fun.service.BatteryCachingService;
import com.spring.fun.usecase.CreateBatteries;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateBatteryAdapter implements CreateBatteries {

    private final BatteryCachingService batteryCachingService;

    public CreateBatteryAdapter(BatteryCachingService batteryCachingService) {
        this.batteryCachingService = batteryCachingService;
    }

    @Override
    public int doBatch(List<Battery> batteries) {
        var existedBatteries = batteryCachingService.getBatteries();

        batteries.addAll(existedBatteries);

        return this.batteryCachingService.createBatteries(batteries).size();
    }
}
