package com.ipcom.demoqdb;

import org.apache.camel.spring.spi.SpringTransactionPolicy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication
public class DemoQDBApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoQDBApplication.class, args);
	}
	
	@Bean(name = "PROPAGATION_REQUIRED")
	public SpringTransactionPolicy propogationRequired(PlatformTransactionManager jtaTransactionManager){
		SpringTransactionPolicy propagationRequired = new SpringTransactionPolicy();
		propagationRequired.setTransactionManager(jtaTransactionManager);
		propagationRequired.setPropagationBehaviorName("PROPAGATION_REQUIRED");
		return propagationRequired;

	}
}
