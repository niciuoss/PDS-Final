package com.alu.ufc.AlugaJa.modelsDTO;

// TODO Refactor

public class ImovelDTO {
	String id;
	float mensalidade;
	String UF;
	boolean alugado;
	String bairro;
	int banheiros;
	int quartos;
	String logradouro;
	int numero;
	String cidade;
	String id_usuario;

	public  ImovelDTO(){

	}
	
	public ImovelDTO(float mensalidade, String UF, boolean alugado, String bairro, int banheiros, int quartos,
					 String logradouro, int numero, String cidade, String id_usuario){

		this.id_usuario = id_usuario;
		this.mensalidade = mensalidade;
		this.cidade = cidade;
		this.UF = UF;
		this.alugado = alugado;
		this.bairro = bairro;
		this.banheiros = banheiros;
		this.quartos = quartos;
		this.logradouro = logradouro;
		this.numero = numero;
	}


	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {this.id = id;}

	public float getMensalidade() {
		return mensalidade;
	}

	public void setMensalidade(float mensalidade) {
		this.mensalidade = mensalidade;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String UF) {
		this.UF = UF;
	}

	public Boolean getAlugado() {
		return alugado;
	}

	public void setAlugado(Boolean alugado) {
		this.alugado = alugado;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getBanheiros() {
		return banheiros;
	}

	public void setBanheiros(int banheiros) {
		this.banheiros = banheiros;
	}

	public int getQuartos() {
		return quartos;
	}

	public void setQuartos(int quartos) {
		this.quartos = quartos;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Imovel [mensalidade=" + mensalidade + ", UF=" + UF + ", alugado=" + alugado + ", bairro=" + bairro
				+ ", banheiros=" + banheiros + ", quartos=" + quartos + ", logradouro=" + logradouro + ", numero="
				+ numero + "]";
	}
	
	
	
}
