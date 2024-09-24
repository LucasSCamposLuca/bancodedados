package org.example.bancoTest;

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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Pessoa(int codigo, String nome, int idade, String email) {
        super();
        this.codigo = codigo;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }

    private int codigo;
    private String nome;
    private int idade;
    private String email;
}
