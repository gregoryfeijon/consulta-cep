package br.com.gregoryfeijon.consultacep.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 05/03/2021 às 00:00:24
 * 
 * @author gregory.feijon
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ViaCepDTO {

	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;
	private boolean erro;
}
