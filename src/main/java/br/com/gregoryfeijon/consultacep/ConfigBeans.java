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

	/**
	 * Bean de configuração do RestTemplate, para definir a forma que os erros serão
	 * tratados, bem como modificar o RequestFactory, para poder trabalhar de forma
	 * mais livre com a ResponseEntity.
	 * 
	 * @param builder - {@linkplain RestTemplateBuilder}
	 * @return {@linkplain RestTemplate}
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		RestTemplate restTemplate = builder.errorHandler(errorHandler).build();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		return restTemplate;
	}

	/**
	 * {@linkplain Bean} de properties criado, para tornar a injeção de propriedades
	 * mais limpa e agrupada por uso.
	 * 
	 * @return {@linkplain ViaCepProperties}
	 */
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
