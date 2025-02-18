package br.com.jjco.Agenda.model;

import jakarta.persistence.*;

@Entity //Entidade do banco de dados
@Table(name = "tb_pessoas") //Nome da tabela no banco
public class Pessoa {

    @Id //Define como ID/ Chave primária no banco
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Define autoincremento
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column
    private String endereco;

    @Column
    private String cep;

    @Column
    private String cidade;

    @Column
    private String uf;

    //Construtor vazio
    public Pessoa(){}

    //Construtor não define o id
    public Pessoa(String nome, String endereco, String cep, String cidade, String uf) {
        this.nome = nome;
        this.endereco = endereco;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    //Sobrescrevendo método toString para exibição de pessoa
    @Override
    public String toString() {
        return "ID: " + getId() +
                "Nome: " + getNome() +
                "Endereço: " + getEndereco() +
                ", " + getCidade() +
                ", " + getUf() +
                " - CEP: " + getCep();
    }
}
