package com.spring.batteries.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.batteries.controller.ApiStatus;
import com.spring.batteries.controller.BaseResponse;
import com.spring.common.model.Battery;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetListOfBatteriesResponse extends BaseResponse {

    @JsonProperty("batteries")
    private List<Battery> batteries;

    @JsonProperty("noOfBatteries")
    private Integer noOfBatteries;

    @JsonProperty("averageWattCapacity")
    private String averageWattCapacity;

    public GetListOfBatteriesResponse (ApiStatus status, String description) {
        super(status, description);
    }

    public GetListOfBatteriesResponse(List<Battery> batteries) {
        this.batteries = batteries;
    }
}
