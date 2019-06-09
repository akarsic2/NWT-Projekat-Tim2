package listmovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ListMovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListMovieApplication.class, args);
	}

}
