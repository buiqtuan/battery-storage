package com.spring.persistence.entity;

import com.spring.common.model.Battery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "BATTERY")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatteryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long postCode;

    private Long wattCapacity;

    public Battery toBattery() {
        return Battery.builder()
                .name(name)
                .postCode(postCode)
                .wattCapacity(wattCapacity)
                .build();
    }
}
