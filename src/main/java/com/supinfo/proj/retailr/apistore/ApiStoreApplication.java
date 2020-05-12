package com.supinfo.proj.retailr.apistore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
public class ApiStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiStoreApplication.class, args);
	}

}
