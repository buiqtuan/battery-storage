package com.spring.fun.usecase;

import com.spring.fun.entity.Battery;

import java.util.List;

public interface CreateBatteries {

    int doBatch(List<Battery> batteries);
}
