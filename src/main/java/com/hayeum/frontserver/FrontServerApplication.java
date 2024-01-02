package com.hayeum.frontserver;

import com.hayeum.frontserver.common.extend.TargetExtend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

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
