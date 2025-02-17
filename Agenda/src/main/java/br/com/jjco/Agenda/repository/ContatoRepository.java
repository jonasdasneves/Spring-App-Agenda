package br.com.jjco.Agenda.repository;

import br.com.jjco.Agenda.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

//Criação de repositório da classe contato, herdando o JPA
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    @Query(value = "SELECT * FROM TB_CONTATOS WHERE PESSOA_ID = :id", nativeQuery = true)
    List<Contato> findByPessoa(@Param("id") Long id);
}
