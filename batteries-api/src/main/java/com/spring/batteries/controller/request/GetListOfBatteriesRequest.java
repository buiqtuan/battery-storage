package com.spring.batteries.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetListOfBatteriesRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("postCode")
    private String postCode;

    @JsonProperty("wattCapacity")
    private String wattCapacity;
}
