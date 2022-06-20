package com.alu.ufc.AlugaJa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alu.ufc.AlugaJa.commons.GenericServiceIMPL;
import com.alu.ufc.AlugaJa.modelsDTO.ImovelDTO;
import com.alu.ufc.AlugaJa.models.Imovel;
import com.alu.ufc.AlugaJa.service.api.ImovelServiceAPI;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;

@Service
public class ImovelServiceIMPL extends GenericServiceIMPL<Imovel, ImovelDTO> implements ImovelServiceAPI {

    @Autowired
    private Firestore firestore;

    public String validarCriarImovel(Imovel imovel) throws Exception {

        String resultado = new String("");

        if (imovel.getBairro() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Bairro' está vazio" + "\n";
        }
        if (imovel.getBanheiros() == 0) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Banheiros' está vazio" + "\n" ;
        }
        if (imovel.getAlugado() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, Campo 'Alugado' não informado" + "\n";
        }
        if (imovel.getCidade() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Cidade' está vazio" + "\n";
        }
        if(imovel.getCidade() != null) {
            if (imovel.getCidade().equals("Banabuiú") || imovel.getCidade().equals("Choró") || imovel.getCidade().equals("Deputado Irapuan Pinheiro") || imovel.getCidade().equals("Ibaretama") ||
                    imovel.getCidade().equals("Ibicuitinga") || imovel.getCidade().equals("Milhã") || imovel.getCidade().equals("Mombaça") || imovel.getCidade().equals("Pedra Branca") ||
                    imovel.getCidade().equals("Piquet Carneiro") || imovel.getCidade().equals("Quixadá") || imovel.getCidade().equals("Quixeramobim") || imovel.getCidade().equals("Senador Pompeu") ||
                    imovel.getCidade().equals("Solonópole")) {
            } else {
                resultado = "Esse aplicativo por enquanto trabalha apenas com a região do sertão central do Ceará, verifique se escreveu corretamente ou aguarde novas atualizações :)" + "\n";
            }
        }
        if (imovel.getLogradouro() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Logradouro' está vazio" + "\n";
        }
        if (imovel.getMensalidade() == 0) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Mensalidade' está vazio" + "\n";
        }
        if (imovel.getUF() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'UF' está vazio" + "\n";
        }
        if(imovel.getUF() != null) {
            if (imovel.getUF().equals("CE")) {
            }
            else {
                resultado = "Esse aplicativo por enquanto trabalha apenas com a região do sertão central do Ceará, aguarde novas atualizações :)" + "\n";
            }
        }

        if (imovel.getQuartos() == 0) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Quartos' está vazio" + "\n";
        }

        if (resultado.equals("")) {
            return "safe";
        }else{
            return resultado;
        }
    }

    public String validarAtualizarImovel(Imovel imovel, ImovelDTO dto) throws Exception{
        String resultado = ("");
        int alt = 0;

        if(dto.getBairro().equals(imovel.getBairro())){} else { alt++; resultado = "Bairro alterado com sucesso" + "\n";}
        if(dto.getCidade().equals(imovel.getCidade())){} else { alt++; resultado = "Cidade alterada com sucesso" + "\n";}
        if(dto.getLogradouro().equals(imovel.getLogradouro())){} else { alt++; resultado = "Logradouro alterado com sucesso" + "\n";}
        if(dto.getMensalidade() == imovel.getMensalidade()){} else { alt++; resultado = "Mensalidade alterada com sucesso" + "\n";}
        if(dto.getBanheiros() == imovel.getBanheiros()){} else { alt++; resultado = "Quantidade de banheiros alterado com sucesso" + "\n";}
        if(dto.getQuartos() == imovel.getQuartos()){} else { alt++; resultado = "Quantidade de Quartos alterado com sucesso" + "\n";}
        if(dto.getNumero() == imovel.getNumero()){} else { alt++; resultado = "Número alterado com sucesso" + "\n";}
        if(dto.getUF().equals(imovel.getUF())){} else { alt++; resultado = "UF alterado com sucesso" + "\n";}
        if(dto.getAlugado().equals(imovel.getAlugado())){} else { alt++; resultado = "Situação do imóvel alterada com sucesso" + "\n";}

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
        return firestore.collection("imoveis");
    }

}