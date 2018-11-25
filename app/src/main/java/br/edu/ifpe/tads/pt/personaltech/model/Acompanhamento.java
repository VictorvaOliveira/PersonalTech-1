package br.edu.ifpe.tads.pt.personaltech.model;

public class Acompanhamento {

    private int peso;
    private int altura;
    private String data;

    public Acompanhamento() {

    }

    public Acompanhamento(int altura, String data, int peso) {
        this.altura = altura;
        this.data = data;
        this.peso = peso;
    }

    public int getPeso() {

        return peso;
    }

    public void setPeso(int peso) {

        this.peso = peso;
    }

    public int getAltura() {

        return altura;
    }

    public void setAltura(int altura) {

        this.altura = altura;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}


