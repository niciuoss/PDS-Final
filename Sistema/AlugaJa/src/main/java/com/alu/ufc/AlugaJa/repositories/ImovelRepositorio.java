package com.alu.ufc.AlugaJa.repositories;

import java.util.ArrayList;
import java.util.List;

import com.alu.ufc.AlugaJa.models.Imovel;


public class ImovelRepositorio {
	List<Imovel> imoveis = new ArrayList<>();
	public void salvar(Imovel imovel) {
		imoveis.add(imovel);			
	}
}
