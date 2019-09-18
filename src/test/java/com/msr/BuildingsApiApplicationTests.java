package com.msr;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.msr.controller.BuildingsController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuildingsApiApplicationTests {

    @Autowired
    private BuildingsController buildingsController;

    @Test
    public void contextLoads() {
        assertThat(buildingsController).isNotNull();
    }

}
