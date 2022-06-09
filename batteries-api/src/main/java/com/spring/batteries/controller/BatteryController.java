package com.spring.batteries.controller;

import com.spring.batteries.controller.request.CreateBatchOfBatteriesRequest;
import com.spring.batteries.controller.response.CreateBatchOfBatteriesResponse;
import com.spring.batteries.controller.response.GetListOfBatteriesResponse;
import com.spring.batteries.usecase.CreateBatteriesUseCase;
import com.spring.batteries.usecase.LoadBatteriesUseCase;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("battery")
@Log4j2
public class BatteryController {

    private final LoadBatteriesUseCase loadBatteriesUseCase;

    private final CreateBatteriesUseCase createBatteriesUseCase;

    public BatteryController(CreateBatteriesUseCase createBatteriesUseCase, LoadBatteriesUseCase loadBatteriesUseCase) {
        this.createBatteriesUseCase = createBatteriesUseCase;
        this.loadBatteriesUseCase = loadBatteriesUseCase;
    }

    @PostMapping("create")
    public ResponseEntity<?> createBatteries(@Validated @RequestBody CreateBatchOfBatteriesRequest request) {
        try {
            this.createBatteriesUseCase.createList(request.getBatteries());

            return ResponseEntity.ok(
                new CreateBatchOfBatteriesResponse(ApiStatus.SUCCESS, String.format("%d battery(ies) has/ve been created", request.getBatteries().size()))
            );
        } catch (Exception e) {
            log.error("battery/create", e);

            return ResponseEntity.badRequest().body(
                new CreateBatchOfBatteriesResponse(ApiStatus.FAIL, e.getMessage())
            );
        }
    }

    @GetMapping("getByPostCodeRange")
    public ResponseEntity<?> getBatteriesByName(@RequestParam Long from, @RequestParam Long to) {
        try {

            var response = this.loadBatteriesUseCase.byPostCodeRange(from, to);

            return ResponseEntity.ok(response);

        } catch (NumberFormatException nfe) {
            log.error("battery/getByName", nfe);

            return ResponseEntity.badRequest().body(
                    new GetListOfBatteriesResponse(ApiStatus.FAIL, "INVALID WATT CAPACITY")
            );
        }
    }
}
