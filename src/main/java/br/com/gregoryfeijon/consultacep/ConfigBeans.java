package br.com.gregoryfeijon.consultacep;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.gregoryfeijon.consultacep.properties.ViaCepProperties;

/**
 * 04/03/2021 às 23:32:43
 * 
 * @author gregory.feijon
 */

@Configuration
public class ConfigBeans {

	@Bean
	@ConfigurationProperties(prefix = "via.cep")
	public ViaCepProperties viaCepProperties() {
		return new ViaCepProperties();
	}
}
