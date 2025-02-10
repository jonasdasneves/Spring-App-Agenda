package br.com.jjco.Agenda.resource;

import br.com.jjco.Agenda.model.Pessoa;
import br.com.jjco.Agenda.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController //Define a classe como controller
@RequestMapping("/api/pessoas") //Define a rota que esse controller atende
public class PessoaResource {

    @Autowired //Injeção de dependência do Service para chamar os métodos
    private PessoaService pessoaService;

    @PostMapping//POST http://localhost:8080/api/pessoas
    public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa){

        //Salva o produto no banco utilizando o método do Service
        Pessoa newPessoa = pessoaService.save(pessoa);

        //Caso o produto tenha retornado null, por erro na requisição
        if(newPessoa == null){
            //Retorna 400, indicando que a requisição não foi feita corretamente
            return ResponseEntity.badRequest().build();
        }
        else {
            //Retorna 200, indicando que a requisição foi um sucesso
            return ResponseEntity.ok(pessoa);
        }

    }

    @GetMapping//GET http://localhost:8080/api/pessoas
    public ResponseEntity<List<Pessoa>> findAll(){

        //Cria uma lista das pessoas encontradas
        List<Pessoa> listaPessoas = pessoaService.findAll();


        if(listaPessoas == null){
            //Se a lista for nula, houve problema na requisição, retornando 400
            return ResponseEntity.badRequest().build();
        }

        else if(listaPessoas.size() == 0) {
            //Se a lista não tiver registros. retorna 404
            return ResponseEntity.notFound().build();
        }
        else {
            //Retorna 200, indicando que a requisição foi um sucesso
            return ResponseEntity.ok(listaPessoas);
        }

    }

}
