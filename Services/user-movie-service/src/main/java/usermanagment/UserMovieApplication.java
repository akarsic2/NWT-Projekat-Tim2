package usermanagment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UserMovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMovieApplication.class, args);
	}

}
