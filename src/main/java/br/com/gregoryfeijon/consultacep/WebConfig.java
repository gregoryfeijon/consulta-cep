package br.com.gregoryfeijon.consultacep;

import br.com.gregoryfeijon.consultacep.annotation.RestAPIController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 07/03/2021 às 15:22:27
 * 
 * @author gregory.feijon
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.addPathPrefix("/api", HandlerTypePredicate.forAnnotation(RestAPIController.class));
	}
}
