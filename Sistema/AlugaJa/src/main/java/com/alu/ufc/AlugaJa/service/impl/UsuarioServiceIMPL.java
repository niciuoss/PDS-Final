package com.alu.ufc.AlugaJa.service.impl;

import com.alu.ufc.AlugaJa.commons.GenericServiceIMPL;
import com.alu.ufc.AlugaJa.models.Usuario;
import com.alu.ufc.AlugaJa.modelsDTO.UsuarioDTO;
import com.alu.ufc.AlugaJa.service.api.UsuarioServiceAPI;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceIMPL extends GenericServiceIMPL<Usuario, UsuarioDTO> implements UsuarioServiceAPI {

    @Autowired
    private Firestore firestore;

    public String validarCriarImovel(Usuario usuario) throws Exception {

        String resultado = new String("");

        if (usuario.getContato() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Bairro' está vazio" + "\n";
        }
        if (usuario.getCpf() == 0) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Banheiros' está vazio" + "\n" ;
        }
        if (usuario.getEmail() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, Campo 'Alugado' não informado" + "\n";
        }
        if (usuario.getCidade() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Cidade' está vazio" + "\n";
        }
        if(usuario.getCidade() != null) {
            if (usuario.getCidade().equals("Banabuiú") || usuario.getCidade().equals("Choró") || usuario.getCidade().equals("Deputado Irapuan Pinheiro") || usuario.getCidade().equals("Ibaretama") ||
                    usuario.getCidade().equals("Ibicuitinga") || usuario.getCidade().equals("Milhã") || usuario.getCidade().equals("Mombaça") || usuario.getCidade().equals("Pedra Branca") ||
                    usuario.getCidade().equals("Piquet Carneiro") || usuario.getCidade().equals("Quixadá") || usuario.getCidade().equals("Quixeramobim") || usuario.getCidade().equals("Senador Pompeu") ||
                    usuario.getCidade().equals("Solonópole")) {
            } else {
                resultado = "Esse aplicativo por enquanto trabalha apenas com a região do sertão central do Ceará, verifique se escreveu corretamente ou aguarde novas atualizações :)" + "\n";
            }
        }
        if (usuario.getSenha() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Logradouro' está vazio" + "\n";
        }
        if (usuario.getNome() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Mensalidade' está vazio" + "\n";
        }
        if (usuario.getUF() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'UF' está vazio" + "\n";
        }
        if(usuario.getUF() != null) {
            if (usuario.getUF().equals("CE")) {
            }
            else {
                resultado = "Esse aplicativo por enquanto trabalha apenas com a região do sertão central do Ceará, aguarde novas atualizações :)" + "\n";
            }
        }
        if (resultado.equals("")) {
            return "safe";
        }else{
            return resultado;
        }
    }

    public String validarAtualizarImovel(Usuario usuario, UsuarioDTO dto) throws Exception{
        String resultado = ("");
        int alt = 0;

        if(dto.getNome().equals(usuario.getNome())){} else { alt++; resultado = "Bairro alterado com sucesso" + "\n";}
        if(dto.getCidade().equals(usuario.getCidade())){} else { alt++; resultado = "Cidade alterada com sucesso" + "\n";}
        if(dto.getCpf() == usuario.getCpf()){} else { alt++; resultado = "Logradouro alterado com sucesso" + "\n";}
        if(dto.getSenha() == usuario.getSenha()){} else { alt++; resultado = "Mensalidade alterada com sucesso" + "\n";}
        if(dto.getEmail() == usuario.getEmail()){} else { alt++; resultado = "Quantidade de banheiros alterado com sucesso" + "\n";}
        if(dto.getContato() == usuario.getContato()){} else { alt++; resultado = "Quantidade de Quartos alterado com sucesso" + "\n";}
        if(dto.getUF().equals(usuario.getUF())){} else { alt++; resultado = "UF alterado com sucesso" + "\n";}

        if(alt == 0){
            resultado = "Não houveram alterações, mude algum campo e tente novamente";
            return resultado;
        }
        else{
            return resultado;
        }
    }

    @Override
    public CollectionReference getCollection() {
        return firestore.collection("usuarios");
    }
}
