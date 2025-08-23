package dev.cmurillo.SmokeNotesBackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmokeNotesBackendApplication {

	private static final Logger log = LoggerFactory.getLogger(SmokeNotesBackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SmokeNotesBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			log.info("Testing application command line runner!");
		};
	}
}
