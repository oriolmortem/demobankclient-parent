package pe.devsu.development.clientservicetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "pe.devsu.development.clientservicetest.*" })
@SpringBootApplication
public class DemoBankClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBankClientApplication.class, args);
	}

}
