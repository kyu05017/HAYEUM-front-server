package com.hayeum.frontserver.common.util;

import org.springframework.beans.factory.annotation.Value;

public class TargetExtend {

	@Value("${target.port.DATABASE}")
	protected static String DATABASE;

	@Value("${target.port.FILE}")
	protected static String FILE;

	@Value("${target.URL.LOCAL}")
	protected static String LOCAL;

	@Value("${target.URL.DEV}")
	protected static String DEV;

	@Value("${target.URL.PROD}")
	protected static String PROD;
}
