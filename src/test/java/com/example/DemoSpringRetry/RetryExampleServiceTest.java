package com.example.DemoSpringRetry;

import com.example.DemoSpringRetry.service.ExampleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RetryExampleServiceTest {

    @Autowired
    private ExampleService exampleService;


    @Test
    public void retryRecoveryTest(){
        log.info("Inicio retryRecoveryTest");
        try {
            String result= exampleService.sendMail("! Hola a todos");
            Assert.assertEquals("OK", result);
        } catch (Exception e) {
            log.error("Error retryRecoveryTest , " , e.getMessage());
        }
    }

}
