package dev.cmurillo.SmokeNotesBackend;

import dev.cmurillo.SmokeNotesBackend.Model.Users.Role;
import dev.cmurillo.SmokeNotesBackend.Model.Users.User;
import dev.cmurillo.SmokeNotesBackend.Repository.UserRepository;
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
	CommandLineRunner runner(UserRepository userRepository) {
		return args -> {
			User user = new User(null, "John", "Smith", "johnsmith@yahoo.com", "johnsemailpassword123.", Role.USER);
			log.info("User John Smith has been created for testing purposes.");
			userRepository.save(user);
		};
	}
}
