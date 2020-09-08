package com.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.spring.boot"})
public class SpringBootWithHibernateEventListenerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithHibernateEventListenerApplication.class, args);
	}
}
