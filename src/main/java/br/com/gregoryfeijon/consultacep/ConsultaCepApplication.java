package br.com.gregoryfeijon.consultacep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class ConsultaCepApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaCepApplication.class, args);
	}

}
