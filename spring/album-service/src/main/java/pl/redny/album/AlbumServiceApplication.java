package pl.redny.album;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableConfigurationProperties
@SpringBootApplication
public class AlbumServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbumServiceApplication.class, args);
	}

}

