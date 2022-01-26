package br.com.gregoryfeijon.consultacep.api.controller;

import br.com.gregoryfeijon.consultacep.ConsultaCepTestHelper;
import br.com.gregoryfeijon.consultacep.api.response.Response;
import br.com.gregoryfeijon.consultacep.model.Endereco;
import br.com.gregoryfeijon.consultacep.model.EnderecoBuilder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * 06/03/2021 às 20:49:10
 * 
 * @author gregory.feijon
 */

@ActiveProfiles("dev")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class ConsultaCepAPIControllerTest extends ConsultaCepTestHelper {

	private static final String MENSAGEM_ERRO = "CEP inválido!";
	
	@Mock
	private RestTemplate restTemplate;

	@Test
	@Order(1)
	public void test00_obtemEnderecoCepValido() {
		EnderecoBuilder enderecoBuilder = new EnderecoBuilder().withCep(CEP_DIGITADO_VALIDO);
		Endereco enderecoRequisicao = enderecoBuilder.build();
		Endereco enderecoRetornoEsperado = criaEndereco(CEP_VALIDO, RUA_VALIDA, BAIRRO, CIDADE, ESTADO);
		Response<Endereco> responseEsperada = new Response<>();
		responseEsperada.setData(enderecoRetornoEsperado);

		HttpEntity<Endereco> httpEntity = new HttpEntity<>(enderecoRequisicao);
		ParameterizedTypeReference<Response<Endereco>> tipoRetorno = new ParameterizedTypeReference<Response<Endereco>>() {
		};

		when(restTemplate.exchange("/api/consulta-cep", HttpMethod.POST, httpEntity, tipoRetorno))
				.thenReturn(new ResponseEntity<Response<Endereco>>(responseEsperada, HttpStatus.OK));

		ResponseEntity<Response<Endereco>> response = restTemplate.exchange("/api/consulta-cep", HttpMethod.POST,
				httpEntity, tipoRetorno);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		verificaRetornoEsperado(enderecoRetornoEsperado, response.getBody().getData());
	}
	
	@Test
	@Order(2)
	public void test01_obtemEnderecoCepValidoMasIncorreto() {
		EnderecoBuilder enderecoBuilder = new EnderecoBuilder().withCep(CEP_DIGITADO_VALIDO_ERRADO);
		Endereco enderecoRequisicao = enderecoBuilder.build();
		Endereco enderecoRetornoEsperado = criaEndereco(CEP_VALIDO_ERRADO, RUA_VALIDA_ERRADA, BAIRRO, CIDADE, ESTADO);
		Response<Endereco> responseEsperada = new Response<>();
		responseEsperada.setData(enderecoRetornoEsperado);

		HttpEntity<Endereco> httpEntity = new HttpEntity<>(enderecoRequisicao);
		ParameterizedTypeReference<Response<Endereco>> tipoRetorno = new ParameterizedTypeReference<Response<Endereco>>() {
		};

		when(restTemplate.exchange("/api/consulta-cep", HttpMethod.POST, httpEntity, tipoRetorno))
				.thenReturn(new ResponseEntity<Response<Endereco>>(responseEsperada, HttpStatus.OK));

		ResponseEntity<Response<Endereco>> response = restTemplate.exchange("/api/consulta-cep", HttpMethod.POST,
				httpEntity, tipoRetorno);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		verificaRetornoEsperado(enderecoRetornoEsperado, response.getBody().getData());
	}
	
	@Test
	@Order(3)
	public void test02_obtemEnderecoCepInvalido() {
		EnderecoBuilder enderecoBuilder = new EnderecoBuilder().withCep(CEP_DIGITADO_INVALIDO);
		Endereco enderecoRequisicao = enderecoBuilder.build();
		Response<Endereco> responseEsperada = new Response<>();
		responseEsperada.setErrors(Arrays.asList(MENSAGEM_ERRO));

		HttpEntity<Endereco> httpEntity = new HttpEntity<>(enderecoRequisicao);
		ParameterizedTypeReference<Response<Endereco>> tipoRetorno = new ParameterizedTypeReference<Response<Endereco>>() {
		};

		when(restTemplate.exchange("/api/consulta-cep", HttpMethod.POST, httpEntity, tipoRetorno))
				.thenReturn(new ResponseEntity<Response<Endereco>>(responseEsperada, HttpStatus.BAD_REQUEST));

		ResponseEntity<Response<Endereco>> response = restTemplate.exchange("/api/consulta-cep", HttpMethod.POST,
				httpEntity, tipoRetorno);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

		assertTrue(response.getBody().getErrors().contains(MENSAGEM_ERRO));
	}
}
