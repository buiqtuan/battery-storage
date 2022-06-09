package com.spring.batteries.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.batteries.controller.ApiStatus;
import com.spring.batteries.controller.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBatchOfBatteriesResponse extends BaseResponse {

    @JsonProperty("noOfBatteriesCreated")
    private Integer noOfBatteriesCreated;

    public CreateBatchOfBatteriesResponse(Integer noOfBatteriesCreated) {
        this.noOfBatteriesCreated = noOfBatteriesCreated;
    }

    public CreateBatchOfBatteriesResponse(ApiStatus status, String description) {
        super(status, description);
    }
}
