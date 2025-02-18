package br.com.jjco.Agenda.resource;

import br.com.jjco.Agenda.model.Contato;
import br.com.jjco.Agenda.model.Pessoa;
import br.com.jjco.Agenda.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController //Define a classe como controller
@RequestMapping("/api/contatos") //Define a rota que esse controller atende
public class ContatoResource {

    @Autowired //Injeção de dependência do Service para chamar os métodos
    private ContatoService contatoService;

    //Registra um novo contato
    @PostMapping//POST http://localhost:8080/api/contatos
    public ResponseEntity<Contato> save(@RequestBody Contato contato){

        Contato newContato = contatoService.save(contato);

        if(newContato == null){
            //Retorna 400, indicando que a requisição não foi feita corretamente
            return ResponseEntity.badRequest().build(); //400
        }
        else {
            //Retorna 200, indicando que a requisição foi um sucesso
            return ResponseEntity.ok(newContato); //200
        }
    }

    //Retorna contato por ID
    @GetMapping("/{id}")//GET http://localhost:8080/api/contatos/id
    public ResponseEntity<Optional<Contato>> findById(@PathVariable Long id){

        Optional<Contato> contato = contatoService.findById(id);

        if(contato.isEmpty()){
            //Se a o objeto estiver vazio, aquele id não existe, retornando 404
            return ResponseEntity.notFound().build(); //404
        }
        else{
            //Retorna 200, indicando que a requisição foi um sucesso
            return ResponseEntity.ok(contato); //200
        }
    }

    //Retorna todos os contatos de uma pessoa pelo ID dessa pessoa
    @GetMapping("/pessoas/{id}")//GET http://localhost:8080/api/contatos/pessoas/id
    public ResponseEntity<List<Contato>> findByIdPessoa(@PathVariable Long id){

        //Cria uma lista com todos os contatos de uma pessoa
        List<Contato> listaContatosPessoa = contatoService.findByIdPessoa(id);
        
        if(listaContatosPessoa.isEmpty()) {
            //Se a lista não tiver registros. retorna 204
            return ResponseEntity.noContent().build(); //204
        }
        else {
            //Retorna 200, indicando que a requisição foi um sucesso
            return ResponseEntity.ok(listaContatosPessoa); //200
        }
    }


    //Retorna todos os registros de contatos
    @GetMapping//GET http://localhost:8080/api/contatos
    public ResponseEntity<List<Contato>> findAll(){

        //Cria uma lista dos contatos encontrados
        List<Contato> listaContatos = contatoService.findAll();

        if(listaContatos.isEmpty()) {
            //Se a lista não tiver registros. retorna 404
            return ResponseEntity.notFound().build();
        }
        else {
            //Retorna 200, indicando que a requisição foi um sucesso
            return ResponseEntity.ok(listaContatos);
        }
    }

    //Atualiza o registro de um contato e seu respectivo dono
    @PutMapping //UPDATE http://localhost:8080/api/pessoas
    public ResponseEntity<Contato> update(@RequestBody Contato contato){

        Contato newContato = contatoService.update(contato);

        if (newContato == null) {
            //Se o objeto for nulo, houve problema na requisição, retornando 400,
            // pois se o objeto apenas não existisse, um novo seria criado
            return ResponseEntity.badRequest().build();
        }
        else {
            //Retorna 200, indicando que a requisição foi um sucesso
            return ResponseEntity.ok(contato);
        }
    }

    //Apaga um contato por seu ID
    @DeleteMapping("/{id}") //DELETE http://localhost:8080/api/contatos/{id}
    public ResponseEntity<?> delete(@PathVariable Long id){
        contatoService.delete(id);
        //Retorna 204. Indicando que o objeto foi apagado
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204
    }


}
