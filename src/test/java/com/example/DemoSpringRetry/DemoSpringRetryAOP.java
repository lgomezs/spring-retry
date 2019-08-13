package com.example.DemoSpringRetry;


import static org.junit.Assert.assertEquals;

import com.example.DemoSpringRetry.service.ExampleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/application-configuration.xml"})
public class DemoSpringRetryAOP {


    @Resource
    private ExampleService exampleService;

    @Test
    public void sendEmailTest(){
        try {
            exampleService.sendMail();
            assertEquals(exampleService.getTimes(),4);
        }catch (Exception exception){
            log.error("Error sendEmailTest , {} ", exception.getMessage() );
        }
    }
}
