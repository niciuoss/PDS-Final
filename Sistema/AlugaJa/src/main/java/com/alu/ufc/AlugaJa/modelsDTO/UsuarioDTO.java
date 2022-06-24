package com.alu.ufc.AlugaJa.modelsDTO;

// TODO Refactor

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO {

	String id;
	String cidade;
	String contato;
	Long cpf;
	String email;
	String nome;
	String senha;
	String UF;
	List<String> id_imovel;
	Data dataNacimento;

	public UsuarioDTO(){}

	public UsuarioDTO(String cidade, String contato, Long cpf, String email, String nome, String senha, String UF, List<String> id_imovel, Data dataNacimento) {
		this.id_imovel = new ArrayList<String>();
		this.dataNacimento = dataNacimento;
		this.cidade = cidade;
		this.contato = contato;
		this.cpf = cpf;
		this.email = email;
		this.nome = nome;
		this.senha = senha;
		this.UF = UF;
	}

	public List<String> getId_imovel() {
		return id_imovel;
	}

	public void setId_imovel(List<String> id_imovel) {
		this.id_imovel = id_imovel;
	}

	public Data getDataNacimento() {
		return dataNacimento;
	}

	public void setDataNacimento(Data dataNacimento) {
		this.dataNacimento = dataNacimento;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getUF(){
		return UF;
	}

	public void setUF(String UF){
		this.UF = UF;
	}
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}
	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario [cidade=" + cidade + ", contato=" + contato + ", cpf=" + cpf + ", email=" + email
				+ ", nome=" + nome + ", senha=" + senha + "]";
	}
	
	
}
