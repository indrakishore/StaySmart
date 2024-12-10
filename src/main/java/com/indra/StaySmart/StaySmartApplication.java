package com.indra.StaySmart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class StaySmartApplication implements CommandLineRunner {

//	@Value("${discount.offer.price}")
//	private int discountPrice;

	@Autowired
	private Environment environment;

	@Value("${spring.profiles.active}")
	private String envArgs;


	public static void main(String[] args) {
		SpringApplication.run(StaySmartApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception{

		System.out.println("Discount price: " + environment.getProperty("discount.offer.price"));

		System.out.println("Enviornment variables: " + environment.getProperty("spring.profiles.active"));

	}

}
