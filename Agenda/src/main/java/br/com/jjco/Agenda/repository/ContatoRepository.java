package br.com.jjco.Agenda.repository;

import br.com.jjco.Agenda.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

//Criação de repositório da classe contato, herdando o JPA
public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
