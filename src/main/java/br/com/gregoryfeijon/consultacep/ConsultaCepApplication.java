package br.com.gregoryfeijon.consultacep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;

@SpringBootApplication
@Configuration
@EnableConfigurationProperties
public class ConsultaCepApplication {

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.ofOffset("GMT", ZoneOffset.of("-3"))));
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ConsultaCepApplication.class, args);
	}

}
