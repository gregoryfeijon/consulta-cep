package br.com.gregoryfeijon.consultacep.model;

import br.com.gregoryfeijon.consultacep.dto.ViaCepDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 04/03/2021 às 21:59:23
 * 
 * @author gregory.feijon
 */

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Endereco implements Serializable {
	
	private static final long serialVersionUID = -6087941180681233737L;
	
	private String cep;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;

	public Endereco(String cep) {
		this.cep = cep;
	}
	
	public Endereco(ViaCepDTO viaCepDTO) {
		this.rua = viaCepDTO.getLogradouro();
		this.bairro = viaCepDTO.getBairro();
		this.cidade = viaCepDTO.getLocalidade();
		this.estado = viaCepDTO.getUf();
	}
}
