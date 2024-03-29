package com.pi.factoring_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources(value = {
		@PropertySource("file:src/main/resources/application.properties")
})
public class FactoringBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoringBackendApplication.class, args);
	} 

}
