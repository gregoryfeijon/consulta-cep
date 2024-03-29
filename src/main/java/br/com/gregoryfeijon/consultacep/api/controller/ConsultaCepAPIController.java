package br.com.gregoryfeijon.consultacep.api.controller;

import br.com.gregoryfeijon.consultacep.annotation.RestAPIController;
import br.com.gregoryfeijon.consultacep.api.response.Response;
import br.com.gregoryfeijon.consultacep.model.Endereco;
import br.com.gregoryfeijon.consultacep.service.ConsultaCepService;
import br.com.gregoryfeijon.consultacep.util.ApiUtil;
import br.com.gregoryfeijon.consultacep.util.CepUtil;
import br.com.gregoryfeijon.consultacep.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 04/03/2021 às 22:04:43
 * 
 * @author gregory.feijon
 */

@RestAPIController("consulta-cep")
@CrossOrigin(origins = "*")
public class ConsultaCepAPIController {

	private static final LoggerUtil LOG = LoggerUtil.getLog(ConsultaCepAPIController.class);

	@Autowired
	private ConsultaCepService consultaCepService;

	@GetMapping("/{cep}")
	public ResponseEntity<Response<Endereco>> buscaEnderecoPorCep(@PathVariable String cep) {
		LOG.info("Consulta do endereço referente ao CEP: {0}", cep);
		return executaConsulta(cep);
	}

	@PostMapping
	public ResponseEntity<Response<Endereco>> buscaEnderecoPorCep(@RequestBody Endereco endereco) {
		LOG.info("Consulta do endereço referente ao CEP: {0}", endereco.getCep());
		return executaConsulta(endereco.getCep());
	}

	private ResponseEntity<Response<Endereco>> executaConsulta(String cep) {
		if (CepUtil.validaCep(cep)) {
			Endereco endereco = consultaCepService.buscaEnderecoPorCep(cep);
			if (endereco == null) {
				return ResponseEntity.badRequest().body(ApiUtil.criarResponseDeErro("Endereço não encontrado!"));
			}
			return ResponseEntity.ok(ApiUtil.criaResponseBody(endereco));
		}
		return ResponseEntity.badRequest().body(ApiUtil.criarResponseDeErro("CEP inválido!"));
	}
}
