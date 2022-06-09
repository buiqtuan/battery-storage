package com.spring.persistence.service;

import com.spring.persistence.entity.BatteryEntity;
import com.spring.persistence.repository.BatteryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest(showSql = false)
@RunWith(SpringRunner.class)
public class CreateBatteriesServiceTest {

    @Autowired
    private BatteryRepository batteryRepository;

    @Test
    public void createBattery_Test() {
        CreateBatteriesService createBatteriesService = new CreateBatteriesService(this.batteryRepository);

        BatteryEntity batteryEntity = BatteryEntity.builder()
                .name("test")
                .postCode(100l)
                .wattCapacity(100l)
                .build();

        createBatteriesService.createBattery(batteryEntity.toBattery());

        var batteryAfterCreated = batteryRepository.findByPostCodeBetweenOrderByNameDesc(100l, 100l).get(0);

        Assert.assertNotNull(batteryAfterCreated);
        Assert.assertEquals(batteryAfterCreated.getName(),"test");
    }
}
