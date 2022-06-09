package com.spring.batteries.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse {

    @JsonProperty("status")
    private ApiStatus status;

    @JsonProperty("description")
    private String description;

    public BaseResponse() {
        this.status = ApiStatus.SUCCESS;
    }

    public BaseResponse(ApiStatus status, String description) {
        this.status = status;
        this.description = description;
    }
}
