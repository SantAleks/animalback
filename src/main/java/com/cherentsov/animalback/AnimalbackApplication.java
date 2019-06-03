package com.cherentsov.animalback;

import com.cherentsov.animalback.Service.DBService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AnimalbackApplication {

	private static final Log logger = LogFactory.getLog(AnimalbackApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(AnimalbackApplication.class, args);
		logger.info("Start Aplication. DB is Empty");
		ExampleData.AddExampleToDB();
		logger.info("DB is Full");
	}
}
