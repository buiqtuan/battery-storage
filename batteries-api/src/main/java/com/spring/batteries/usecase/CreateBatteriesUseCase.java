package com.spring.batteries.usecase;


import com.spring.common.model.Battery;

import java.util.List;

public interface CreateBatteriesUseCase {

    void createSingle(Battery battery);

    void createList(List<Battery> batteries);
}
