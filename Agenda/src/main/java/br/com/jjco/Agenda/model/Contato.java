package br.com.jjco.Agenda.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity //Entidade do banco de dados
@Table(name = "tb_contatos") //Nome da tabela no banco
public class Contato {

    //Cria um enum com possiveis tipos de contato
    private enum TipoContato { telefone, celular, email, redeSocial };

    @Id //Define como ID/ Chave primária no banco
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Define autoincremento
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoContato tipoContato;

    @Column(nullable = false)
    private String contato;

    //Relação manyToOne cria uma coluna de pessoa_id como chave estrangeira de id
    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @JsonBackReference // Impede que a pessoa seja serializada no JSON do endereço
    private Pessoa pessoa;

    //Construtor vazio
    public Contato(){}

    //Construtor não define o id
    public Contato(TipoContato tipoContato, String contato, Pessoa pessoa) {
        this.tipoContato = tipoContato;
        this.contato = contato;
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String toString() {
        return "ID do contato: " + getContato() + ", Tipo - " + getTipoContato() + ", Pessoa: " + getPessoa();
    }
}
