package com.alu.ufc.AlugaJa.modelsDTO;

public class ContratoDTO {

    String id;
    String id_usuario;

    String id_Imovel;

    String validade;

    float valorImovel;

    public ContratoDTO() {
    }

    public ContratoDTO(String cpfUsuario, String id_Imovel, String validade, float valorImovel) {
        this.id_usuario = cpfUsuario;
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

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
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