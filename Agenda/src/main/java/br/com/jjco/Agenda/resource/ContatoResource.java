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

@RestController
@RequestMapping("/api/contatos")
public class ContatoResource {

    @Autowired //Injeção de dependência
    private ContatoService contatoService;

    @PostMapping//POST http://localhost:8080/api/contatos
    public ResponseEntity<Contato> save(@RequestBody Contato contato){
        Contato newContato = contatoService.save(contato);
        if(newContato == null)
            return ResponseEntity.badRequest().build(); //400
        return ResponseEntity.ok(newContato);
    }

    @GetMapping("/{id}")//GET http://localhost:8080/api/contatos/id
    public ResponseEntity<Optional<Contato>> findById(@PathVariable Long id){

        Optional<Contato> contato = contatoService.findById(id);

        if(contato.isEmpty()){
            //Se a o objeto estiver vazio, aquele id não existe, retornando 404
            return ResponseEntity.notFound().build();
        }
        if (contato == null){
            //Se o objeto for nulo, houve problema na requisição, retornando 400
            return ResponseEntity.badRequest().build();
        }
        else{
            //Retorna 200, indicando que a requisição foi um sucesso
            return ResponseEntity.ok(contato);
        }
    }

    @GetMapping("/pessoas/{id}")//GET http://localhost:8080/api/contatos/pessoas/id
    public ResponseEntity<List<Contato>> findByIdPessoa(@PathVariable Long id){
        
        List<Contato> listaContatosPessoa = contatoService.findByIdPessoa(id);
        
        if(listaContatosPessoa.isEmpty()) {
            //Se a lista não tiver registros. retorna 404
            return ResponseEntity.noContent().build();
        }
        else if(listaContatosPessoa == null) {
            //Se a lista não tiver registros. retorna 404
            return ResponseEntity.notFound().build();
        }
        else {
            //Retorna 200, indicando que a requisição foi um sucesso
            return ResponseEntity.ok(listaContatosPessoa);
        }
    }


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

    @DeleteMapping("/{id}") //DELETE http://localhost:8080/api/contatos/{id}
    public ResponseEntity<?> delete(@PathVariable Long id){
        contatoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204
    }


}
