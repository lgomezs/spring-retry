package com.example.DemoSpringRetry.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RetryTemplateExampleService {

    private final RetryTemplate retryTemplate;
    @Autowired
    private ExampleService exampleService;

    @Autowired
    public RetryTemplateExampleService(RetryTemplate retryTemplate) {
        this.retryTemplate = retryTemplate;
    }


    public String sendEmail(String email){
        return  retryTemplate.execute(new RetryCallback<String, RuntimeException>() {
            @Override
            public String doWithRetry(RetryContext context){
                log.info("Init doWithRetry");
                return exampleService.sendMail(email);
            }
        });
    }

}
