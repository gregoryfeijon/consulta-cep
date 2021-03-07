package br.com.gregoryfeijon.consultacep.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 07/03/2021 às 18:11:40
 * 
 * @author gregory.feijon
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@RestController
@RequestMapping
public @interface RestAPIController {

	@AliasFor(annotation = RequestMapping.class, attribute = "value")
	String[] value() default "";
}