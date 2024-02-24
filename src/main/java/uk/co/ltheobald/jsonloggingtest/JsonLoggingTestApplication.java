package uk.co.ltheobald.jsonloggingtest;

import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JsonLoggingTestApplication {
	public static final Logger LOGGER = LoggerFactory.getLogger(JsonLoggingTestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JsonLoggingTestApplication.class, args);
		LOGGER.info("Application started");
	}


}
