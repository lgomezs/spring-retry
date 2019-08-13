package com.example.DemoSpringRetry.service;

public interface ExampleService {

    public String sendMail(String mail)throws  Exception;

    public String recoverMessage(RuntimeException exception, String message);
}
