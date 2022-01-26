package br.com.gregoryfeijon.consultacep.util;

import br.com.gregoryfeijon.consultacep.api.response.Response;
import br.com.gregoryfeijon.consultacep.exception.APIException;

import java.util.Arrays;
import java.util.List;

/**
 * 06/03/2021 às 21:12:54
 * 
 * @author gregory.feijon
 */

public final class ApiUtil {

	private static final LoggerUtil LOG = LoggerUtil.getLog(ApiUtil.class);

	private ApiUtil() {}

	public static <T> Response<T> criarResponseDeErro(APIException ex) {
		return criarResponseDeErro(ex.getMessage());
	}

	public static <T> Response<T> criarResponseDeErro(String erro) {
		return criarResponseDeErro(Arrays.asList(erro));
	}

	public static <T> Response<T> criarResponseDeErro(List<String> erros) {
		Response<T> response = new Response<>();
		response.setErrors(erros);
		erros.stream().forEach(erro -> LOG.warning(erro));
		return response;
	}

	public static <T> Response<T> criaResponseBody(T entity) {
		Response<T> response = new Response<>();
		response.setData(entity);
		return response;
	}
}
