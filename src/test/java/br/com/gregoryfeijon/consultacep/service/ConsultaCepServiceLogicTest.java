package br.com.gregoryfeijon.consultacep.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.gregoryfeijon.consultacep.ConsultaCepTestHelper;
import br.com.gregoryfeijon.consultacep.model.Endereco;
import br.com.gregoryfeijon.consultacep.util.StringUtil;

/**
 * 06/03/2021 às 20:49:10
 * 
 * @author gregory.feijon
 */

@ActiveProfiles("dev")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class ConsultaCepServiceLogicTest extends ConsultaCepTestHelper {

	@Test
	@Order(1)
	public void test00_buscaEnderecoValido() {
		Endereco enderecoRetornoEsperado = criaEndereco(CEP_VALIDO, RUA_VALIDA, BAIRRO, CIDADE, ESTADO);

		doReturn(Optional.of(enderecoRetornoEsperado)).when(cepConsumer)
				.buscaEnderecoPorCep(StringUtil.somenteNumeros(CEP_DIGITADO_VALIDO));

		Endereco enderecoRetorno = consultaCepService.buscaEnderecoPorCep(CEP_DIGITADO_VALIDO);

		verificaRetornoEsperado(enderecoRetornoEsperado, enderecoRetorno);
	}

	@Test
	@Order(2)
	public void test01_buscaEnderecoValidoMasErrado() {
		Endereco enderecoRetornoEsperado = criaEndereco(CEP_VALIDO_ERRADO, RUA_VALIDA_ERRADA, BAIRRO, CIDADE, ESTADO);

		doReturn(Optional.empty()).when(cepConsumer)
				.buscaEnderecoPorCep(StringUtil.somenteNumeros(CEP_DIGITADO_VALIDO_ERRADO));
		doReturn(Optional.of(enderecoRetornoEsperado)).when(cepConsumer)
				.buscaEnderecoPorCep(CEP_ULTIMO_NUMERO_TROCADO_POR_ZERO);

		Endereco enderecoRetorno = consultaCepService.buscaEnderecoPorCep(CEP_DIGITADO_VALIDO_ERRADO);

		verificaRetornoEsperado(enderecoRetornoEsperado, enderecoRetorno);
	}

	@Test
	@Order(3)
	public void test02_buscaEnderecoInvalido() {
		doReturn(Optional.empty()).when(cepConsumer)
				.buscaEnderecoPorCep(StringUtil.somenteNumeros(CEP_DIGITADO_INVALIDO));

		Endereco enderecoRetorno = consultaCepService.buscaEnderecoPorCep(CEP_DIGITADO_INVALIDO);

		assertNull(enderecoRetorno);
	}
}
