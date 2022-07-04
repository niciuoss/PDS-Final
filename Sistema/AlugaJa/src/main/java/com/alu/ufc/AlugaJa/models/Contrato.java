package com.alu.ufc.AlugaJa.models;

public class Contrato {
    String id_locatario;
    String id_Imovel;
    String validade;
    float valorImovel;

    public Contrato() {
    }

    public Contrato(String id_locatario, String id_Imovel, String validade, float valorImovel) {
        this.id_locatario = id_locatario;
        this.id_Imovel = id_Imovel;
        this.validade = validade;
        this.valorImovel = valorImovel;
    }

    public String getId_locatario() {
        return id_locatario;
    }

    public void setId_locatario(String id_usuario) {
        this.id_locatario = id_locatario;
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