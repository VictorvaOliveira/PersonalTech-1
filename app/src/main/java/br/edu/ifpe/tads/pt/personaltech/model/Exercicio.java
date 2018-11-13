package br.edu.ifpe.tads.pt.personaltech.model;

public class Exercicio {

    private String nome;
    private String descricao;
    private String imagemId;
    private String nivel;
    private String tipo;
    public Exercicio(String nome, String descricao, String imagemId){

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

    public String getImagemId() {
        return imagemId;
    }

    public void setImagemId(String imagemId) {
        this.imagemId = imagemId;
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
