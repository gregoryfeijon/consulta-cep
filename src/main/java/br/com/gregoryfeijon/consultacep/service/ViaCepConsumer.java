package br.com.gregoryfeijon.consultacep.service;

import br.com.gregoryfeijon.consultacep.api.response.Response;
import br.com.gregoryfeijon.consultacep.dto.ViaCepDTO;
import br.com.gregoryfeijon.consultacep.properties.ViaCepProperties;
import br.com.gregoryfeijon.consultacep.util.LoggerUtil;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * 04/03/2021 às 22:49:10
 * 
 * @author gregory.feijon
 */

@RestController
public class ViaCepConsumer extends BuscaCepAPICliente<ViaCepDTO> {

	private static final LoggerUtil LOG = LoggerUtil.getLog(ViaCepConsumer.class);

	private String apiReturnType;
	
	public ViaCepConsumer(ViaCepProperties properties) {
		this.initialPath = properties.getInitialPath();
		this.apiReturnType = properties.getApiReturnType();
	}

	@Override
	public Optional<ViaCepDTO> consultaEnderecoPorCep(String cep) {
		String path = initialPath + cep + "/" + apiReturnType;
		ResponseEntity<ViaCepDTO> response = requestUtil.getEntity(path, ViaCepDTO.class);
		if (response.getStatusCodeValue() == 200) {
			ViaCepDTO viaCepDTO = response.getBody();
			LOG.info("Informações obtidas do CEP {0}: {1}", cep, viaCepDTO);
			if (viaCepDTO.isErro()) {
				return Optional.empty();
			}
			return Optional.of(viaCepDTO);
		}
		return Optional.empty();
	}

	@Override
	protected ParameterizedTypeReference<Response<ViaCepDTO>> returnType() {
		return new ParameterizedTypeReference<Response<ViaCepDTO>>() {
		};
	}

}
