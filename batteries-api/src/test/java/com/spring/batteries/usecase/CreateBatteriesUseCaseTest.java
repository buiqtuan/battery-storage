package com.spring.batteries.usecase;

import com.spring.batteries.MainApplication;
import com.spring.persistence.entity.BatteryEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MainApplication.class)
@RunWith(SpringRunner.class)
public class CreateBatteriesUseCaseTest {

    @Autowired
    private CreateBatteriesUseCase createBatteriesUseCase;

    @Autowired
    private LoadBatteriesUseCase loadBatteriesUseCase;

    @Test
    public void setCreateBatteriesUseCase_Test() {
        BatteryEntity batteryEntity = BatteryEntity.builder()
                .name("test")
                .postCode(100l)
                .wattCapacity(100l)
                .build();

        createBatteriesUseCase.createSingle(batteryEntity.toBattery());

        var response = loadBatteriesUseCase.byPostCodeRange(100l, 100l);

        Assert.assertNotNull(response);
        Assert.assertEquals(1, response.getNoOfBatteries().intValue());
    }
}
