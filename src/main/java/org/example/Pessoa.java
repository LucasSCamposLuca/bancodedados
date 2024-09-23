package org.example;

public class Pessoa {
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pessoa(int codigo, String nome) {
        super();
        this.codigo = codigo;
        this.nome = nome;
    }

    private int codigo;
    private String nome;
}
