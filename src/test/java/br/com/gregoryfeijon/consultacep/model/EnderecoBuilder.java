package br.com.gregoryfeijon.consultacep.model;

/**
 * 07/03/2021 às 19:00:06
 * 
 * @author gregory.feijon
 */

public class EnderecoBuilder {

	private Endereco enderecoBuilder = new Endereco();

	public void novo() {
		this.enderecoBuilder = new Endereco();
	}

	public Endereco build() {
		return enderecoBuilder;
	}

	public EnderecoBuilder withCep(String cep) {
		enderecoBuilder.setCep(cep);
		return this;
	}

	public EnderecoBuilder withRua(String rua) {
		enderecoBuilder.setRua(rua);
		return this;
	}

	public EnderecoBuilder withBairro(String bairro) {
		enderecoBuilder.setBairro(bairro);
		return this;
	}

	public EnderecoBuilder withCidade(String cidade) {
		enderecoBuilder.setCidade(cidade);
		return this;
	}

	public EnderecoBuilder withEstado(String estado) {
		enderecoBuilder.setEstado(estado);
		return this;
	}
}
