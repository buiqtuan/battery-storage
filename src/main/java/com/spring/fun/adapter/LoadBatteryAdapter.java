package com.spring.fun.adapter;

import com.spring.fun.entity.Battery;
import com.spring.fun.service.BatteryCachingService;
import com.spring.fun.usecase.LoadBatteries;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoadBatteryAdapter implements LoadBatteries {

    private final BatteryCachingService batteryCachingService;

    private List<Battery> batteries;

    public LoadBatteryAdapter(BatteryCachingService batteryCachingService) {
        this.batteryCachingService = batteryCachingService;
    }

    @Override
    public List<Battery> byName(String name) {
        this.batteries = this.batteryCachingService.getBatteries();

        if (name == null || name.isEmpty() || name.isBlank()) {
            return this.batteries;
        }

        return this.batteries.stream().filter(b -> b.getName().contains(name)).collect(Collectors.toList());
    }

    @Override
    public List<Battery> byPostCode(String postCode) {
        this.batteries = this.batteryCachingService.getBatteries();

        return this.batteries.stream().filter(b -> b.getPostCode().contains(postCode)).collect(Collectors.toList());
    }

    @Override
    public List<Battery> byWattCapacity(Long wattCapacity) {
        this.batteries = this.batteryCachingService.getBatteries();

        return this.batteries.stream().filter(b -> b.getWattCapacity() == wattCapacity).collect(Collectors.toList());
    }

    @Override
    public List<Battery> byNameAndPostCodeAndWattCapacity(String name, String postCode, Long wattCapacity) {
        this.batteries = this.batteryCachingService.getBatteries();

        return this.batteries.stream().filter(b -> b.isMatchedWith(name, postCode, wattCapacity)).collect(Collectors.toList());
    }

    @Override
    public List<Battery> all() {
        return this.batteryCachingService.getBatteries();
    }
}
