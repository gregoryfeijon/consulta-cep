package br.com.gregoryfeijon.consultacep.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.gregoryfeijon.consultacep.model.Endereco;

@Component
public class CepConsumer {

	@Autowired
	private ViaCepConsumer viaCepConsumer;

	@Value("${via-cep.busca-endereco}")
	private boolean usaViaCep;

	public Optional<Endereco> buscaEnderecoPorCep(String cep) {
		Endereco[] endereco = { null };
		if (usaViaCep) {
			viaCepConsumer.consultaEnderecoPorCep(cep).ifPresent(viaCepDTO -> endereco[0] = new Endereco(viaCepDTO));
		}
		if (endereco[0] != null) {
			return Optional.of(endereco[0]);
		}
		return Optional.empty();
	}
}
