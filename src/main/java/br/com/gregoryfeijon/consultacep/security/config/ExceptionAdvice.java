package br.com.gregoryfeijon.consultacep.security.config;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.gregoryfeijon.consultacep.api.response.Response;
import br.com.gregoryfeijon.consultacep.exception.APIException;
import br.com.gregoryfeijon.consultacep.util.ApiUtil;

/**
 * 06/03/2021 às 22:06:27
 * 
 * @author gregory.feijon
 */

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { AccessDeniedException.class })
	public final <T> ResponseEntity<Response<T>> notAllowed() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiUtil.criarResponseDeErro("Access not allowed"));
	}

	@ExceptionHandler(APIException.class)
	public final <T> ResponseEntity<Response<T>> handleInternalException(APIException ex) {
		return ResponseEntity.badRequest().body(ApiUtil.criarResponseDeErro(ex));
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		StringBuilder sb = new StringBuilder("Erros de validação dos dados inseridos:\n");
		errors.stream().forEach(erro -> sb.append(erro.getDefaultMessage()));
		return ResponseEntity.badRequest().body(ApiUtil.criarResponseDeErro(sb.toString()));
	}
}
