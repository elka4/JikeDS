package com.jike.spring.chapter06;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContextConfig {
	@Bean
	public String message() {
		return "Hello World!";
	}

}