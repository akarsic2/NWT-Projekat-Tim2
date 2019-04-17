package com.example.moviegenreservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableRabbit
@SpringBootApplication

public class MovieGenreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieGenreServiceApplication.class, args);
	}

}
