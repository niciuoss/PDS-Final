package com.alu.ufc.AlugaJa.models;

//TODO FALTA AJEITAR O BANCO DE DADOS, LÁ TEM O TIPO 'DATA', EU NÃO SEI MEXER COM ISSO

import java.util.ArrayList;

public class Contrato {

    String id;
    String cpfUsuario;

    String id_Imovel;

    String validade;

    float valorImovel;

    public Contrato() {
    }

    public Contrato(String cpfUsuario, String id_Imovel, String validade, float valorImovel) {
        this.cpfUsuario = cpfUsuario;
        this.id_Imovel = id_Imovel;
        this.validade = validade;
        this.valorImovel = valorImovel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getId_imovel() {
        return id_Imovel;
    }

    public void setId_imovel(String id_Imovel) {
        this.id_Imovel = id_Imovel;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public float getValorImovel() {
        return valorImovel;
    }

    public void setValorImovel(float valorImovel) {
        this.valorImovel = valorImovel;
    }
}