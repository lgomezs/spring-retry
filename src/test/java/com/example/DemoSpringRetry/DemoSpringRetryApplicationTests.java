package com.example.DemoSpringRetry;


import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.example.DemoSpringRetry.service.ExampleService;
import com.example.DemoSpringRetry.service.ExampleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.Times;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSpringRetryApplicationTests {

	private ExampleService exampleService;
	private RetryTemplate retryTemplate;

	@Before
	public void init(){
		retryTemplate= new RetryTemplate();

		Map<Class<? extends Throwable>, Boolean> supportedExceptionsMap =
				new HashMap<>();
		supportedExceptionsMap.put(Exception.class, true);

		//Política de reintentos
		RetryPolicy retryPolicy = new SimpleRetryPolicy(5, supportedExceptionsMap);

		//Política BackOff
		FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();

		//5000ms -> 5s
		backOffPolicy.setBackOffPeriod(5000);

		retryTemplate.setRetryPolicy(retryPolicy);
		retryTemplate.setBackOffPolicy(backOffPolicy);

		exampleService = spy(new ExampleServiceImpl());

	}

	@Test
	public void givenServiceMethodRetrytable(){
		try {

			log.info("trying to invoke send email method ...");

			retryTemplate.execute(new RetryCallback() {
				public String doWithRetry(RetryContext arg0) throws Exception {
					log.info("retry args getRetryCount , {} ", arg0.getRetryCount());
					return exampleService.sendMail();
				}
			});

			verify(exampleService, times(5)).sendMail();
			verifyNoMoreInteractions(exampleService);

		}catch (Exception exception){
			log.error("Error givenServiceMethodRetrytable : {} " , exception.getMessage());
		}
	}

}
