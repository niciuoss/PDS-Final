package com.alu.ufc.AlugaJa.models;

public class Usuario {
	static private Long ultimoId = 1L;
	long Id;
	String cidade;
	String contato;
	int cpf;
	String email;
	String nome;
	String senha;
	public Usuario(String cidade, String contato, int cpf, String email, String nome, String senha) {
		Id = ultimoId++;
		this.cidade = cidade;
		this.contato = contato;
		this.cpf = cpf;
		this.email = email;
		this.nome = nome;
		this.senha = senha;
	}
	public long getId() {
		return Id;
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
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
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
		return "Usuario [Id=" + Id + ", cidade=" + cidade + ", contato=" + contato + ", cpf=" + cpf + ", email=" + email
				+ ", nome=" + nome + ", senha=" + senha + "]";
	}
	
	
}
