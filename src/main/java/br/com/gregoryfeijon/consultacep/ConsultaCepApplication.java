package br.com.gregoryfeijon.consultacep;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Configuration
@EnableConfigurationProperties
@EnableSwagger2
public class ConsultaCepApplication {

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.ofOffset("GMT", ZoneOffset.of("-3"))));
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ConsultaCepApplication.class, args);
	}

}
