package br.com.gregoryfeijon.consultacep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;

import br.com.gregoryfeijon.consultacep.api.response.Response;
import br.com.gregoryfeijon.consultacep.util.RequestUtil;

/**
 * 04/03/2021 às 22:47:51
 * 
 * @author gregory.feijon
 */

public abstract class APICliente<T> {

	@Autowired
	protected RequestUtil<T> requestUtil;

	protected String initialPath;
	protected String apiReturnType;
	
	protected abstract ParameterizedTypeReference<Response<T>> returnType();
}
