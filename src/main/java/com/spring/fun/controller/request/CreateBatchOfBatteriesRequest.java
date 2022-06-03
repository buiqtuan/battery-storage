package com.spring.fun.controller.request;

import com.spring.fun.entity.Battery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBatchOfBatteriesRequest {

    @NotNull
    @NotEmpty
    private List<Battery> batteries;
}
