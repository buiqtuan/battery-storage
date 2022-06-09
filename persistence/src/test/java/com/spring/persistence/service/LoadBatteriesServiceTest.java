package com.spring.persistence.service;

import com.spring.common.model.Battery;
import com.spring.persistence.entity.BatteryEntity;
import com.spring.persistence.repository.BatteryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest(showSql = false)
@RunWith(SpringRunner.class)
public class LoadBatteriesServiceTest {

    @Autowired
    private BatteryRepository batteryRepository;

    private List<Battery> batteries = new ArrayList<>();

    @Before
    public void createBatteries() {
        CreateBatteriesService createBatteriesService = new CreateBatteriesService(this.batteryRepository);

        for (int i=1;i<=5;i++) {
            BatteryEntity batteryEntity = BatteryEntity.builder()
                    .name("test")
                    .postCode(100l*i)
                    .wattCapacity(100l)
                    .build();

            batteries.add(batteryEntity.toBattery());
        }

        createBatteriesService.createBatteries(this.batteries);
    }

    @Test
    public void loadBattery_Test() {
        LoadBatteriesService loadBatteriesService = new LoadBatteriesService(this.batteryRepository);

        var batteries = loadBatteriesService.byPostCodeRange(100l,300l);

        Assert.assertNotNull(batteries);
        Assert.assertEquals(batteries.size(),3);
    }
}
