package com.hayeum.frontserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@Slf4j
@SpringBootApplication
public class FrontServerApplication {

	public static final String APPLICATION_LOCATIONS =
			"spring.config.location="
					+ "classpath:application.yml,"
					+ "classpath:/yml/serverSetting.yml";
	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(FrontServerApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
		log.info("********** Start Front Server **********");
		log.info("At : [{}]",System.currentTimeMillis());
		log.info("****************************************");
	}
}
