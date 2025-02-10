package br.com.jjco.Agenda.repository;

import br.com.jjco.Agenda.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

//Criação de repositório da classe pessoas, herdando o JPA
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {


}
