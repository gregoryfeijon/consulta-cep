package br.com.gregoryfeijon.consultacep.service;

import java.util.Optional;

/**
 * 06/03/2021 às 20:49:10
 * 
 * @author gregory.feijon
 */

public abstract class BuscaCepAPICliente<T> extends APICliente<T> {

	public abstract Optional<T> consultaEnderecoPorCep(String cep);
}
