package com.alu.ufc.AlugaJa.models;

// TODO Refactor

import javax.xml.crypto.Data;
import java.util.ArrayList;


public class Usuario {
	String cidade;
	String contato;
	Long cpf;
	String email;
	String nome;
	String senha;
	ArrayList<String> id_imovel;
	Data dataNacimento;
	String UF;

	public Usuario(String cidade, String contato, Long cpf, String email, String nome, String senha, String UF, Data dataNacimento, ArrayList<String> id_imovel) {
		this.id_imovel = new ArrayList<>();
		this.dataNacimento = dataNacimento;
		this.cidade = cidade;
		this.contato = contato;
		this.cpf = cpf;
		this.email = email;
		this.nome = nome;
		this.senha = senha;
		this.UF = UF;
	}

	public ArrayList<String> getId_imovel() {
		return id_imovel;
	}

	public void setId_imovel(String id) {
		this.id_imovel.add(id);
	}

	public Data getDataNacimento() {
		return dataNacimento;
	}

	public void setDataNacimento(Data dataNacimento) {
		this.dataNacimento = dataNacimento;
	}

	public String getUF() {
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
	
}
