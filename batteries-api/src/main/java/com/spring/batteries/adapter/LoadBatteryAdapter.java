package com.spring.batteries.adapter;

import com.spring.batteries.controller.ApiStatus;
import com.spring.batteries.controller.response.GetListOfBatteriesResponse;
import com.spring.batteries.usecase.LoadBatteriesUseCase;
import com.spring.persistence.service.LoadBatteriesService;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;


@Component
public class LoadBatteryAdapter implements LoadBatteriesUseCase {

    private final LoadBatteriesService loadBatteriesService;

    public LoadBatteryAdapter(LoadBatteriesService loadBatteriesService) {
        this.loadBatteriesService = loadBatteriesService;
    }

    @Override
    public GetListOfBatteriesResponse byPostCodeRange(Long from, Long to) {

        var batteries = this.loadBatteriesService.byPostCodeRange(from, to);

        if (batteries == null || batteries.size() == 0) {
            new GetListOfBatteriesResponse(ApiStatus.SUCCESS, "");
        }

        AtomicReference<Long> totalWattCapacity = new AtomicReference<>(0l);

        batteries.forEach(c -> totalWattCapacity.updateAndGet(v -> v + c.getWattCapacity()));

        var response = new GetListOfBatteriesResponse(batteries);
        response.setNoOfBatteries(batteries.size());
        response.setAverageWattCapacity(String.valueOf(Math.round(totalWattCapacity.get() / response.getNoOfBatteries())));

        return response;
    }
}
