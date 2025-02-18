package br.com.jjco.Agenda.service;

import br.com.jjco.Agenda.model.Contato;
import br.com.jjco.Agenda.model.Pessoa;
import br.com.jjco.Agenda.repository.ContatoRepository;
import br.com.jjco.Agenda.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired //Injeção de dependência
    private ContatoRepository contatoRepository;

    @Autowired //Injeção de dependência
    private PessoaRepository pessoaRepository;


    //CRUD - create
    public Contato save(Contato contato){
        //O ID não deve ser passado no método Save, para evitar sobrescrever registros
        if(contato.getId() != null){
            System.out.println("O ID não deve ser enviado para salvar um registro.");
            return null;
        }
        // Caso o contato esteja vazio
        if (contato.getContato() == null){
            System.out.println("Insira o contato a ser registrado.");
            return null;
        }

        // Caso o tipo do contato esteja vazio
        if (contato.getTipoContato() == null){
            System.out.println("Insira o tipo do contato a ser registrado (telefone ou celular).");
            return null;
        }
        //verificar se a pessoa existe, caso não, avisar:
        if(contato.getPessoa().getId() != null) {
            //buscar a pessoa no banco de dados pelo método do proprio pessoaRepository:
            Optional<Pessoa> findPessoa = pessoaRepository
                            .findById(contato.getPessoa().getId());


            //Caso o a busca não seja nula, mas o registro estiver vazio
            if(findPessoa.isEmpty()) {
                System.out.println("Pessoa não encontrada");
                return null;
            }else {
                //Tenta salvar pessoa no banco e aponta erros se houver
                try {
                    contato.setPessoa(findPessoa.get());
                    return contatoRepository.save(contato);
                } catch (Exception e) {
                    System.out.println("Erro ao inserir contato " +
                            contato + ": " + e.getMessage());
                    return null;
                }
            }

        }else {
            //Caso a pessoa não exista, não é possivel criar o contato
            System.out.println("Pessoa não existe");
            return null;
        }
    }

    //CRUD - Read - Retorna contato pelo ID
    //Utiliza o Optional para evitar erros
    public Optional<Contato> findById(Long id){
        return contatoRepository.findById(id);
    }

    //CRUD - Read - Retorna todos os registros de contatos
    public List<Contato> findAll(){
        return contatoRepository.findAll();
    }

    //CRUD - Read - Retorna todos os contatos de uma pessoa
    public List<Contato> findByIdPessoa(Long id){

        //Cria uma lista para ser retornada com os contatos da pessoa
        return contatoRepository.findByPessoa(id);
    }

    //CRUD - Update - Atualiza um registro
    public Contato update(Contato contato){

        //procura um registro com mesmo ID do passado por requisição
        Optional<Contato> findContato = contatoRepository.findById(contato.getId());

        //Se o registro já existe
        if (findContato.isPresent()){

            //Cria novo objeto com os dados da requisição
            Contato updContato = findContato.get();

            //Atualiza o registro existente com os dados novos
            updContato.setTipoContato(contato.getTipoContato());
            updContato.setContato(contato.getContato());
            updContato.setPessoa(contato.getPessoa());

            //Registra atualização de pessoa no banco
            pessoaRepository.save(contato.getPessoa());

            //Registra atualização de contato no banco
            return contatoRepository.save(updContato);
        }

        //Se não existe o registro, um registro novo é criado
        else {
            return contatoRepository.save(contato);
        }
    }

    //CRUD - Delete
    public void delete(Long id) {
        //deleta registro pelo ID
        contatoRepository.deleteById(id);
    }
}
