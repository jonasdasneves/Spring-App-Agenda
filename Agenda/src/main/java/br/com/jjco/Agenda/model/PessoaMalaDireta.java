package br.com.jjco.Agenda.model;

public record PessoaMalaDireta(Long id, String nome, String enderecoMalaDireta) {

    public PessoaMalaDireta(Pessoa pessoa){
        this(pessoa.getId(), pessoa.getNome(), pessoa.getEndereco() + " - CEP: " + pessoa.getCep() + " - " + pessoa.getCidade() + "/" + pessoa.getEndereco());
    }
}
