package com.example.DemoSpringRetry.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExampleServiceImpl implements  ExampleService{

    private int times = 0;

    @Override
    public String sendMail() throws Exception {
        log.info("Inicio send mail ");

        if(times<4){
            times++;
           throw  new Exception("Retrying email sending");
        }

        log.info("Retrying send email");
        log.info("email enviado .....");
        return "OK";
    }

    @Override
    public int getTimes() {
        return times;
    }

}
