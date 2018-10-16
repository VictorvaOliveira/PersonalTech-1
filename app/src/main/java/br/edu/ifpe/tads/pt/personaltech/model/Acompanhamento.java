package br.edu.ifpe.tads.pt.personaltech.model;

import java.util.Date;

public class Acompanhamento {

    private double peso;
    private double altura;
    private Date dataAcompanhamento;
    private int identificadorAluno;

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public Date getDataAcompanhamento() {
        return dataAcompanhamento;
    }

    public void setDataAcompanhamento(Date dataAcompanhamento) {
        this.dataAcompanhamento = dataAcompanhamento;
    }

    public int getIdentificadorAluno() {
        return identificadorAluno;
    }

    public void setIdentificadorAluno(int identificadorAluno) {
        this.identificadorAluno = identificadorAluno;
    }
}


