package br.com.gregoryfeijon.consultacep.security.config;

import java.nio.file.AccessDeniedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.gregoryfeijon.consultacep.api.response.Response;
import br.com.gregoryfeijon.consultacep.util.ApiUtil;

/**
 * 06/03/2021 às 22:06:27
 * 
 * @author gregory.feijon
 */

@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(value = { AccessDeniedException.class })
	public <T> ResponseEntity<Response<T>> notAllowed() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiUtil.criarResponseDeErro("Access not allowed"));
	}
}
