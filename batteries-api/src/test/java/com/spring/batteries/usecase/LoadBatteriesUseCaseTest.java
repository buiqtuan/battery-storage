package com.spring.batteries.usecase;

import com.spring.batteries.MainApplication;
import com.spring.common.model.Battery;
import com.spring.persistence.entity.BatteryEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest(classes = MainApplication.class)
@RunWith(SpringRunner.class)
public class LoadBatteriesUseCaseTest {

    @Autowired
    private CreateBatteriesUseCase createBatteriesUseCase;

    @Autowired
    private LoadBatteriesUseCase loadBatteriesUseCase;

    private List<Battery> batteries = new ArrayList<>();

    @Before
    public void createBatteries() {

        for (int i=1;i<=5;i++) {
            BatteryEntity batteryEntity = BatteryEntity.builder()
                    .name("test"+i)
                    .postCode(100l*i)
                    .wattCapacity(100l)
                    .build();

            batteries.add(batteryEntity.toBattery());
        }

        Collections.shuffle(this.batteries);

        createBatteriesUseCase.createList(this.batteries);
    }

    @Test
    public void setLoadBatteriesUseCase_Test() {
        var response = loadBatteriesUseCase.byPostCodeRange(100l,500l);

        Assert.assertNotNull(response);
        Assert.assertEquals(5, response.getNoOfBatteries().intValue());
        Assert.assertEquals("test2", response.getBatteries().get(3).getName());
    }
}
