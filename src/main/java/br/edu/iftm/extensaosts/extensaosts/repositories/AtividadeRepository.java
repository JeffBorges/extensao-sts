package br.edu.iftm.extensaosts.extensaosts.repositories;

import br.edu.iftm.extensaosts.extensaosts.domain.Atividade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Integer> {
  List<Atividade> findByNomeIgnoreCaseContaining(@Param("nome") String nome);
  Page<Atividade> findByNomeIgnoreCaseContaining(@Param("nome") String nome, Pageable page);
}
