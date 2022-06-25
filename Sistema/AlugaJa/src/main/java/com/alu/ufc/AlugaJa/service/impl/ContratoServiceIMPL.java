package com.alu.ufc.AlugaJa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alu.ufc.AlugaJa.commons.GenericServiceIMPL;
import com.alu.ufc.AlugaJa.models.Contrato;
import com.alu.ufc.AlugaJa.modelsDTO.ContratoDTO;
import com.alu.ufc.AlugaJa.service.api.ContratoServiceAPI;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;

@Service
public class ContratoServiceIMPL extends GenericServiceIMPL<Contrato, ContratoDTO> implements ContratoServiceAPI {

    @Autowired
    private Firestore firestore;

    public String validarCriarContrato (Contrato contrato, String id_imovel) throws Exception {
        String resultado = new String ("");

        if (contrato.getCpfUsuario() == null) {
            resultado = "Você precisa preencher todos os campos para criar o contrato, o campo 'CPF' está vazio" + "\n";
        }
        if (contrato.getId_imovel() == null) {
            resultado = "Não foi possível registrar o imóvel. MOTIVO: Imóvel não encontrado no banco de dados" + "\n";
        }
        if (contrato.getValidade() == null) {
            resultado = "Você precisa preencher todos os campos para criar o contrato, o campo 'Validade' está vazio" + "\n";
        }
        if (contrato.getValorImovel() == 0) {
            resultado = "Você precisa preencher todos os campos para criar o contrato, o campo 'ValorImovel' está vazio" + "\n";
        }

        if (resultado.equals("")){
            return "safe";
        } else {
            return resultado;
        }
    }

    public String validarAtualizarContrato (Contrato contrato, ContratoDTO dto) throws Exception{
        String resultado = ("");
        int alt = 0;

        if (dto.getCpfUsuario().equals(contrato.getCpfUsuario())){} else {alt++; resultado = "CPF alterado com sucesso" + '\n';}
        if (dto.getValidade().equals(contrato.getValidade())){} else {alt++; resultado = "Validade alterada com sucesso" + '\n';}
        if (dto.getValorImovel() == (contrato.getValorImovel())){} else {alt++; resultado = "ValorImóvel alterado com sucesso" + '\n';}

        if (alt == 0) {
            resultado = "Não houveram alterações, mude algum campo e tente novamente";
            return resultado;
        } else {
            return resultado;
        }

    }
    @Override
    public CollectionReference getCollection() {
        return firestore.collection("contratos");
    }
}
