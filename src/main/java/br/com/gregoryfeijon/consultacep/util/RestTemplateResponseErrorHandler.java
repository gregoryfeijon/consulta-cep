package br.com.gregoryfeijon.consultacep.util;

import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

/**
 * 06/03/2021 às 21:29:04
 * 
 * @author gregory.feijon
 */

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return (response.getStatusCode().series() == Series.CLIENT_ERROR
				|| response.getStatusCode().series() == Series.SERVER_ERROR);
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		switch (response.getStatusCode()) {
		case FORBIDDEN:
			throw new AccessDeniedException("Usuário não autorizado");
		case INTERNAL_SERVER_ERROR:
			throw new InternalError("Erro Interno");
		default:
			break;
		}
	}
}
