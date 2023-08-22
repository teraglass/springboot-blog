package me.tera.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringbootDevApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDevApplication.class, args);
	}

}
