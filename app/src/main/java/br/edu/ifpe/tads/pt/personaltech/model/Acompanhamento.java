package br.edu.ifpe.tads.pt.personaltech.model;

import java.util.Date;

public class Acompanhamento {

    private double peso;
    private double altura;
    private String data;

    public Acompanhamento(){

    }
    public Acompanhamento(double altura, String data, double peso){
        this.altura = altura;
        this.data = data;
        this.peso = peso;
    }
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
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
}


