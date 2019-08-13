package com.example.DemoSpringRetry.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExampleServiceImpl implements  ExampleService{

    private int times = 0;

    @Override
    public String sendMail(String mail) throws RuntimeException {
        log.info("Inicio send mail ");

        if(times<4){
            times++;
            throw  new RuntimeException("Error retrying email sending " );
        }

        log.info("Retrying send email");
        log.info("email enviado  {} ", mail);
        return "OK";
    }
}
