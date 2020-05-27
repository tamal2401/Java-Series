package com.java.spring.aop;

import com.java.spring.aop.annotation.LogTieExecution;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAopApplication {

	private final ApplicationContext context;

	public SpringAopApplication(ApplicationContext context){
		this.context = context;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringAopApplication.class, args);
	}

	@LogTieExecution
	@Bean
	public void laggMethod() throws InterruptedException {
		Thread.sleep(1000);
		SpringApplication.exit(context, new ExitCodeGenerator[0]);
	}
}
