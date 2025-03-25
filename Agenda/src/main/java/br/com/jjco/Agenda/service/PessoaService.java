package br.com.jjco.Agenda.service;

import br.com.jjco.Agenda.exception.BadRequestException;
import br.com.jjco.Agenda.exception.InternalServerException;
import br.com.jjco.Agenda.exception.NotFoundException;
import br.com.jjco.Agenda.model.Pessoa;
import br.com.jjco.Agenda.model.PessoaMalaDireta;
import br.com.jjco.Agenda.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //Define a classe como Service
public class PessoaService {

    @Autowired //Injeção de dependência
    private PessoaRepository pessoaRepository;


    //CRUD - create
    public Pessoa save(Pessoa pessoa){

        if (pessoa.getId() != null){
            throw new BadRequestException("O ID não deve ser passado ao salvar pessoa");
        }
        //Apenas o nome é obrigatório, caso esteja vazio, deve ser apontado erro
        if (pessoa.getNome() == null){
            throw new BadRequestException("O Nome da pessoa não deve estar vazio");
        }
        //Tenta salvar pessoa no banco e aponta erros se houver
        try {
            return pessoaRepository.save(pessoa);
        }
        catch (Exception e){
            throw new InternalServerException("Erro interno de servidor!");
        }
    }

    //CRUD - Read - Retorna pessoa pelo ID
    //Utiliza o Optional para evitar erros
    public Pessoa findById(Long id){
        try {
            return pessoaRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Usuário com ID " + id + " não encontrado"));
        } catch (NotFoundException e) {
            throw e;
        }
        catch (Exception e) {
            throw new InternalServerException("Erro interno ao buscar usuário: " + e.getMessage());
        }
    }

    //CRUD - Read - Retorna todos os registros de pessoa
    public List<Pessoa> findAll(){

        return pessoaRepository.findAll();
    }

    //CRUD - Read - Retorna pessoa com endereço no formato mala direta
    public PessoaMalaDireta findMalaDiretaById(Long id){

        try {
            Pessoa pessoaEncontrada = pessoaRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Usuário com ID " + id + " não encontrado"));

            //Retorna um Record com o padraão de mala direta
            return new PessoaMalaDireta(pessoaEncontrada);

        } catch (NotFoundException e) {
            throw e;
        }
        catch (Exception e) {
            throw new InternalServerException("Erro interno ao buscar usuário: " + e.getMessage());
        }
    }

    //CRUD - Update - Verifica se registro existe pelo id e atualiza ou cria novo
    public Pessoa update(Pessoa pessoa){

        //procura um registro com mesmo ID do passado por requisição
        Optional<Pessoa> findPessoa = pessoaRepository.findById(pessoa.getId());

        //Se o registro já existe
        if (findPessoa.isPresent()){

            //Cria novo objeto com os dados da requisição
            Pessoa updPessoa = findPessoa.get();

            //Atualiza o registro existente com os dados novos
            updPessoa.setNome(pessoa.getNome());
            updPessoa.setCep(pessoa.getCep());
            updPessoa.setEndereco(pessoa.getEndereco());
            updPessoa.setCidade(pessoa.getCidade());
            updPessoa.setUf(pessoa.getUf());

            //Registra atualização no banco
            return pessoaRepository.save(updPessoa);
        }

        //Se não existe o registro, um registro novo é criado
        else {
            return pessoaRepository.save(pessoa);
        }
    }

    //CRUD - Delete
    public void delete(Long id) {
        //deleta registro pelo ID
        pessoaRepository.deleteById(id);
    }

}
