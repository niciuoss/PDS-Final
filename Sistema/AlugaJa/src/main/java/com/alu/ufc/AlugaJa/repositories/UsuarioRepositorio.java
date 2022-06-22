package com.alu.ufc.AlugaJa.repositories;

import java.util.ArrayList;
import java.util.List;
import com.alu.ufc.AlugaJa.models.Usuario;

public class UsuarioRepositorio {
	private List<Usuario> usuarios = new ArrayList<>();
	
	public void salvar(Usuario usuario) {
		usuarios.add(usuario);			
	}
	
	//implementar formas de buscas :)

}
