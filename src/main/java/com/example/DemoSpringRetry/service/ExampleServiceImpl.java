package com.example.DemoSpringRetry.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExampleServiceImpl implements  ExampleService{

    private int times = 0;

    /**
     * maximo de intentos = 4
     * demora entre intentos de 1 seg
     * @return
     * @throws Exception
     */
    @Retryable(value = {RuntimeException.class},maxAttempts = 5, backoff = @Backoff(1000))
    @Override
    public String sendMail(String mail) throws Exception {
        log.info("Inicio send mail ");

        if(times<4){
            times++;
           throw  new RuntimeException("Retrying email sending");
        }

        log.info("Retrying send email");
        log.info("email enviado  {} ", mail);
        return "OK";
    }

    @Recover
    @Override
    public String recoverMessage(RuntimeException exception, String message) {
        log.info(String.format(" ####################    Retry recover - %s " , exception.getMessage()));
        log.info(" message reovery : {} ", message);
        return message;
    }


}
