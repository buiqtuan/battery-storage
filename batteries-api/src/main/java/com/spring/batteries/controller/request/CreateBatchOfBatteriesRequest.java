package com.spring.batteries.controller.request;

import com.spring.common.model.Battery;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class CreateBatchOfBatteriesRequest {

    @NotNull
    @NotEmpty
    private List<Battery> batteries;
}
