package com.spring.batteries.usecase;

import com.spring.batteries.controller.response.GetListOfBatteriesResponse;
import com.spring.common.model.Battery;

import java.util.List;

public interface LoadBatteriesUseCase {

    GetListOfBatteriesResponse byPostCodeRange(Long from, Long to);
}
