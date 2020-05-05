package dev.yidafu.auncel.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AuncelWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuncelWebApplication.class, args);
	}

}
