package br.edu.ifpe.tads.pt.personaltech.model;

public class Exercicio {

    private String nome;
    private String descricao;
    private String imagem;
    private String nivel;
    private String tipo;

    public Exercicio(){

    }

    public Exercicio(String nome, String descricao, String imagem, String tipo, String nivel){
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.tipo = tipo;
        this.nivel = nivel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getTipo(){ return tipo;}

    public void setTipo(String tipo){this.tipo =tipo;}

    @Override
    public String toString() {
        return nome;
    }
}
