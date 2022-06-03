package com.spring.fun.entity;

import lombok.*;

import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Battery {

    private String name;

    private String postCode;

    private Long wattCapacity;

    public String getLowerCaseName() {
        return this.name.toLowerCase(Locale.ROOT);
    }

    public boolean isMatchedWith(String name, String postCode, Long wattCapacity) {
        return this.name.contains(name)
                && this.postCode.contains(postCode)
                && this.wattCapacity == wattCapacity;
    }
}
