package br.com.gregoryfeijon.consultacep.service;

import java.util.Optional;

/**
 * 04/03/2021 às 23:19:04
 * 
 * @author gregory.feijon
 */

public interface CepConsumer<T> {

	public Optional<T> consultaEnderecoPorCep(String cep);
}
