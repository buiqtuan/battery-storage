package com.spring.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Battery {

    private String name;

    private Long postCode;

    private Long wattCapacity;

    public String getLowerCaseName() {
        return this.name.toLowerCase(Locale.ROOT);
    }
}
