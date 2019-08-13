package com.example.DemoSpringRetry;

import com.example.DemoSpringRetry.service.RetryTemplateExampleService;
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
    private RetryTemplateExampleService retryTemplateExampleService;


    @Test
    public void retryExample(){
        String resul= retryTemplateExampleService.sendEmail("!Hola");
        Assert.assertEquals("OK",resul);
    }

}
