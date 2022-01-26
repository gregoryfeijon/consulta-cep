package br.com.gregoryfeijon.consultacep.service;

import br.com.gregoryfeijon.consultacep.model.Endereco;
import br.com.gregoryfeijon.consultacep.util.CepUtil;
import br.com.gregoryfeijon.consultacep.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 04/03/2021 às 22:03:27
 * 
 * @author gregory.feijon
 */

@Service
public class ConsultaCepService {

	@Autowired
	private CepConsumer cepConsumer;

	/**
	 * Método para realizar buscas de endereço a partir de um CEP.
	 * 
	 * @param cep - {@linkplain String}
	 * @return {@linkplain Endereco}
	 */
	public Endereco buscaEnderecoPorCep(String cep) {
		Endereco endereco = null;
		cep = StringUtil.somenteNumeros(cep);
		Optional<Endereco> opRetorno = buscaEndereco(cep);
		if (opRetorno.isPresent()) {
			endereco = opRetorno.get();
			endereco.setCep(cep);
		}
		return endereco;
	}

	/**
	 * Método que orquestra a busca por CEP, manipulando-o até obter um retorno, ou
	 * até alterar todos os caracteres e não obtem nenhum endereço.
	 * 
	 * @param cep - {@linkplain String}
	 * @return {@linkplain Optional}&lt{@linkplain Endereco}&gt
	 */
	private Optional<Endereco> buscaEndereco(String cep) {
		String cepAux = new String(cep);
		Optional<Endereco> opRetorno = Optional.empty();
		int aux = 0;
		while (!opRetorno.isPresent() && aux < cepAux.length()) {
			opRetorno = cepConsumer.buscaEnderecoPorCep(cepAux);
			if (!opRetorno.isPresent()) {
				cepAux = CepUtil.trocaUltimoNumeroPorZero(cepAux);
			}
			aux++;
		}
		return opRetorno;
	}
}
