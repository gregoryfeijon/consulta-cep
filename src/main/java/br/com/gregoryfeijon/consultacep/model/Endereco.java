package br.com.gregoryfeijon.consultacep.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 04/03/2021 às 21:59:23
 * 
 * @author gregory.feijon
 */

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Endereco {

	private String cep;

	@JsonAlias("logradouro")
	private String rua;

	private String bairro;

	@JsonAlias("localidade")
	private String cidade;

	@JsonAlias("uf")
	private String estado;

	public Endereco(String cep) {
		this.cep = cep;
	}
}
