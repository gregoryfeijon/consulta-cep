package br.com.gregoryfeijon.consultacep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import br.com.gregoryfeijon.consultacep.properties.ViaCepProperties;
import br.com.gregoryfeijon.consultacep.security.properties.JwtProperties;
import br.com.gregoryfeijon.consultacep.util.RestTemplateResponseErrorHandler;

/**
 * 04/03/2021 às 23:32:43
 * 
 * @author gregory.feijon
 */

@Configuration
public class ConfigBeans {

	@Autowired
	private RestTemplateResponseErrorHandler errorHandler;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		RestTemplate restTemplate = builder.errorHandler(errorHandler).build();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		return restTemplate;
	}

	@Bean
	@ConfigurationProperties(prefix = "via-cep")
	public ViaCepProperties viaCepProperties() {
		return new ViaCepProperties();
	}

	@Bean
	@ConfigurationProperties(prefix = "jwt-prop")
	public JwtProperties jwtProperties() {
		return new JwtProperties();
	}
}
