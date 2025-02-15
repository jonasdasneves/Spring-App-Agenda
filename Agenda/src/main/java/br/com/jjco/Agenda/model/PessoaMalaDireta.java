package br.com.jjco.Agenda.model;

public record PessoaMalaDireta(Long id, String nome, String enderecoMalaDireta) {

    //Construtor recebe a classe pessoa e define os atributos, criando o endereço em mala direta
    public PessoaMalaDireta(Pessoa pessoa){
        this(pessoa.getId(), pessoa.getNome(), pessoa.getEndereco() +
                                                        " - CEP: " + pessoa.getCep() +
                                                        " - " + pessoa.getCidade() +
                                                        "/" + pessoa.getEndereco());
    }
}
