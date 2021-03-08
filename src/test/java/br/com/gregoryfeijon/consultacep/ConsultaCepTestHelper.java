package br.com.gregoryfeijon.consultacep;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.gregoryfeijon.consultacep.model.Endereco;
import br.com.gregoryfeijon.consultacep.model.EnderecoBuilder;
import br.com.gregoryfeijon.consultacep.service.CepConsumer;
import br.com.gregoryfeijon.consultacep.service.ConsultaCepService;

/**
 * 07/03/2021 às 22:07:30
 * 
 * @author gregory.feijon
 */

public abstract class ConsultaCepTestHelper {

	@Mock
	protected CepConsumer cepConsumer;

	@InjectMocks
	protected ConsultaCepService consultaCepService;

	protected static final String CEP_VALIDO = "13324275";
	protected static final String RUA_VALIDA = "Rua dos Corumbatás";
	protected static final String BAIRRO = "Salto de São José";
	protected static final String CIDADE = "Salto";
	protected static final String ESTADO = "SP";

	protected static final String CEP_VALIDO_ERRADO = "13324279";
	protected static final String RUA_VALIDA_ERRADA = "Rua das Piabas";

	protected static final String CEP_DIGITADO_VALIDO = "13324-275";
	protected static final String CEP_DIGITADO_VALIDO_ERRADO = "13324-279";
	protected static final String CEP_ULTIMO_NUMERO_TROCADO_POR_ZERO = "13324270";
	protected static final String CEP_DIGITADO_INVALIDO = "9999--99999";

	protected Endereco criaEndereco(String cep, String rua, String bairro, String cidade, String estado) {
		EnderecoBuilder enderecoBuilder = new EnderecoBuilder().withCep(cep).withRua(rua).withBairro(bairro)
				.withCidade(cidade).withEstado(estado);
		Endereco endereco = enderecoBuilder.build();
		return endereco;
	}

	protected void verificaRetornoEsperado(Endereco enderecoRetornoEsperado, Endereco enderecoRetorno) {
		assertEquals(enderecoRetornoEsperado.getCep(), enderecoRetorno.getCep());
		assertEquals(enderecoRetornoEsperado.getRua(), enderecoRetorno.getRua());
		assertEquals(enderecoRetornoEsperado.getBairro(), enderecoRetorno.getBairro());
		assertEquals(enderecoRetornoEsperado.getCidade(), enderecoRetorno.getCidade());
		assertEquals(enderecoRetornoEsperado.getEstado(), enderecoRetorno.getEstado());
	}
}
