package com.spring.fun.usecase;

import com.spring.fun.entity.Battery;

import java.util.List;

public interface LoadBatteries {

    List<Battery> byName(String name);

    List<Battery> byPostCode(String postCode);

    List<Battery> byWattCapacity(Long wattCapacity);

    List<Battery> byNameAndPostCodeAndWattCapacity(String name, String postCode, Long wattCapacity);

    List<Battery> all();
}
