package br.com.gregoryfeijon.consultacep.service;

import java.util.Optional;

/**
 * 06/03/2021 às 20:49:10
 * 
 * <p>
 * Classe que define um tipo para todas as integrações de busca de CEP. estende
 * da {@linkplain APICliente}, que é a classe pai para qualquer integração, com
 * todos os métodos necessários.
 * <p>
 * 
 * @see {@linkplain APICliente}
 * 
 * @author gregory.feijon
 */

public abstract class BuscaCepAPICliente<T> extends APICliente<T> {

	public abstract Optional<T> consultaEnderecoPorCep(String cep);
}
