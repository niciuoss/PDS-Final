package com.alu.ufc.AlugaJa.controllers;

import com.alu.ufc.AlugaJa.models.Usuario;
import com.alu.ufc.AlugaJa.repositories.UsuarioRepositorio;

public class UsuarioControlador {
	private UsuarioRepositorio usuarioRepo;
	
	public UsuarioControlador(UsuarioRepositorio usuarioRepositorio) throws Exception {
		if(usuarioRepositorio == null){
			throw new Exception("repo nulo");
		}
		this.usuarioRepo = usuarioRepositorio;
    }
	
	public Usuario criarUsuario(String cidade, String contato, int cpf, String email, String nome, String senha) {
		Usuario usuario = new Usuario(cidade, contato, cpf, email, nome, senha);
		usuarioRepo.salvar(usuario);
		return usuario;
	}
	
	
}