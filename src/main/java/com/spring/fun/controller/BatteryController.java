package com.spring.fun.controller;

import com.spring.fun.controller.request.CreateBatchOfBatteriesRequest;
import com.spring.fun.controller.request.GetListOfBatteriesRequest;
import com.spring.fun.controller.response.CreateBatchOfBatteriesResponse;
import com.spring.fun.controller.response.GetListOfBatteriesResponse;
import com.spring.fun.usecase.CreateBatteries;
import com.spring.fun.usecase.LoadBatteries;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("battery")
@Log4j2
public class BatteryController {

    private final CreateBatteries createBatteries;

    private final LoadBatteries loadBatteries;

    public BatteryController(CreateBatteries createBatteries, LoadBatteries loadBatteries) {
        this.createBatteries = createBatteries;
        this.loadBatteries = loadBatteries;
    }

    @PostMapping("create")
    public ResponseEntity<?> createBatteries(@Validated @RequestBody CreateBatchOfBatteriesRequest request) {
        try {
            int noOfCreatedBatteries = this.createBatteries.doBatch(request.getBatteries());

            return ResponseEntity.ok(
                new CreateBatchOfBatteriesResponse(ApiStatus.SUCCESS, String.format("%d battery(ies) has/ve been created", noOfCreatedBatteries))
            );
        } catch (Exception e) {
            log.error("battery/create", e);

            return ResponseEntity.badRequest().body(
                new CreateBatchOfBatteriesResponse(ApiStatus.FAIL, e.getMessage())
            );
        }
    }

    @GetMapping("getByName")
    public ResponseEntity<?> getBatteriesByName(@RequestParam(required = false) String name) {
        try {

            AtomicReference<Long> totalWattCapacity = new AtomicReference<>(0l);

            var listBatteriesByName = this.loadBatteries.byName(name);

            if (listBatteriesByName == null || listBatteriesByName.size() == 0) {
                return ResponseEntity.ok(new GetListOfBatteriesResponse(ApiStatus.SUCCESS, ""));
            }

            listBatteriesByName.forEach(c -> totalWattCapacity.updateAndGet(v -> v + c.getWattCapacity()));

            var response = new GetListOfBatteriesResponse(listBatteriesByName);
            response.setNoOfBatteries(listBatteriesByName.size());
            response.setAverageWattCapacity(String.valueOf(Math.round(totalWattCapacity.get() / response.getNoOfBatteries())));

            return ResponseEntity.ok(response);

        } catch (NumberFormatException nfe) {
            log.error("battery/getByName", nfe);

            return ResponseEntity.badRequest().body(
                    new GetListOfBatteriesResponse(ApiStatus.FAIL, "INVALID WATT CAPACITY")
            );
        }
    }
}
