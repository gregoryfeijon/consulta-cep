package br.com.gregoryfeijon.consultacep.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 06/03/2021 às 20:49:10
 * 
 * @author gregory.feijon
 */

@Getter
@Setter
public abstract class CepProperties {

	private String initialPath;
	private boolean buscaEndereco;
}
