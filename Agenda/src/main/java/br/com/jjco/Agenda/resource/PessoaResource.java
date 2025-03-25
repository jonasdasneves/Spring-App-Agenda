package br.com.jjco.Agenda.resource;

import br.com.jjco.Agenda.model.Pessoa;
import br.com.jjco.Agenda.model.PessoaMalaDireta;
import br.com.jjco.Agenda.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController //Define a classe como controller
@RequestMapping("/api/pessoas") //Define a rota que esse controller atende
public class PessoaResource {

    @Autowired //Injeção de dependência do Service para chamar os métodos
    private PessoaService pessoaService;

    @Operation(summary = "Cria novo registro de pessoa, não enviar ID, o mesmo é adicionado automaticamente" )
    @PostMapping//POST http://localhost:8080/api/pessoas
    public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa){

        //Salva o produto no banco utilizando o método do Service
        Pessoa newPessoa = pessoaService.save(pessoa);

        //Caso o produto tenha retornado null, por erro na requisição
        if(newPessoa == null){
            //Retorna 400, indicando que a requisição não foi feita corretamente
            return ResponseEntity.badRequest().build(); //404
        }
        else {
            //Retorna 200, indicando que a requisição foi um sucesso
            return ResponseEntity.ok(pessoa); //200
        }

    }

    @Operation(summary = "Retorna pessoa pelo ID")
    @GetMapping("/{id}")//GET http://localhost:8080/api/pessoas/{id}
    public ResponseEntity<Pessoa> findById(@PathVariable Long id){

        Pessoa newPessoa = pessoaService.findById(id);

        return ResponseEntity.ok(newPessoa); //200
    }

    @Operation(summary = "Retorna todas as pessoas")
    @GetMapping//GET http://localhost:8080/api/pessoas
    public ResponseEntity<List<Pessoa>> findAll(){

        //Cria uma lista das pessoas encontradas
        List<Pessoa> listaPessoas = pessoaService.findAll();

        if(listaPessoas.isEmpty()) {
            //Se a lista não tiver registros. retorna 204
            return ResponseEntity.noContent().build(); //204
        }
        else {
            //Retorna 200, indicando que a requisição foi um sucesso
            return ResponseEntity.ok(listaPessoas); //200
        }

    }
    @Operation(summary = "Retorna pessoa pelo ID no formato mala direta")
    @GetMapping("/maladireta/{id}")//GET http://localhost:8080/api/pessoas/maladireta/{id}
    public ResponseEntity<PessoaMalaDireta> findMalaDiretaById(@PathVariable Long id){

        PessoaMalaDireta pessoaMalaDireta = pessoaService.findMalaDiretaById(id);

        return ResponseEntity.ok(pessoaMalaDireta); //200

    }

    @Operation(summary = "Atualiza o registro da pessoa com objeto pessoa, enviar apenas ID existente")
    @PutMapping //UPDATE http://localhost:8080/api/pessoas
    public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa){

        Pessoa newPessoa = pessoaService.update(pessoa);

        if (newPessoa == null) {
            //Se o objeto for nulo, houve problema na requisição, retornando 400,
            // pois se o objeto apenas não existisse, um novo seria criado
            return ResponseEntity.badRequest().build(); //400
        }
        else {
            //Retorna 200, indicando que a requisição foi um sucesso
            return ResponseEntity.ok(pessoa); //200
        }
    }

    @Operation(summary = "Deleta o registro por ID")
    @DeleteMapping("/{id}") //DELETE http://localhost:8080/api/pessoas/{id}
    public ResponseEntity<?> delete(@PathVariable Long id){
        pessoaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204
    }

}
