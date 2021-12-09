package com.joaomarcos.cadastromoradoresderua.model;

public class MoradorDeRua {

    private String nome;
    private String orientacaoSexual;
    private String dataNascimento ;
    private String raca;
    private String sexo;

    public MoradorDeRua(String nome, String orientacaoSexual, String dataNascimento, String raca, String sexo) {
        this.nome = nome;
        this.orientacaoSexual = orientacaoSexual;
        this.dataNascimento = dataNascimento;
        this.raca = raca;
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOrientacaoSexual() {
        return orientacaoSexual;
    }

    public void setOrientacaoSexual(String orientacaoSexual) {
        this.orientacaoSexual = orientacaoSexual;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
