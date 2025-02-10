package br.com.jjco.Agenda.service;

import br.com.jjco.Agenda.model.Pessoa;
import br.com.jjco.Agenda.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //Define a classe como Service
public class PessoaService {

    @Autowired //Injeção de dependência
    private PessoaRepository pessoaRepository;


    //CRUD - create
    public Pessoa save(Pessoa pessoa){

        //Apenas o nome é obrigatório, caso esteja vazio, deve ser apontado erro
        if (pessoa.getNome() == null){
            System.out.println("Insira o nome da pessoa a ser registrada.");
            return null;
        }
        //Tenta salvar pessoa no banco e aponta erros se houver
        try {
            return pessoaRepository.save(pessoa);
        }
        catch (Exception e){
            System.out.println("Erro ao inserir produto " +
                    pessoa + ": " + e.getMessage());
            return null;
        }
    }

    //CRUD - Read - Retorna todos os registros de pessoa
    public List<Pessoa> findAll(){
        return pessoaRepository.findAll();
    }

}
