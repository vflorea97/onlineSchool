package ro.mycode.onlineSchool;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlineSchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineSchoolApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {

		};
	}

}
