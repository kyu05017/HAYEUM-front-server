package com.hayeum.frontserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class FrontServerApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(FrontServerApplication.class, args);
		log.info("********** Start Front Server **********");
		log.info("At : [{}]",System.currentTimeMillis());
		log.info("****************************************");
	}
}
