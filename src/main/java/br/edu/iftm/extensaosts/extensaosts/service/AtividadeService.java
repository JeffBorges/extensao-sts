package br.edu.iftm.extensaosts.extensaosts.service;

import br.edu.iftm.extensaosts.extensaosts.domain.Atividade;
import br.edu.iftm.extensaosts.extensaosts.repositories.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtividadeService {

  private final AtividadeRepository repository;

  @Autowired
  public AtividadeService(AtividadeRepository repository) {
    this.repository = repository;
  }

  public Optional<Atividade> buscar(Integer id) {
    return repository.findById(id);
  }

  public Atividade salvar (Atividade atividade) {
    return repository.save(atividade);
  }

  public void deletar(Integer id) {
    repository.deleteById(id);
  }

  public Page<Atividade> findByName(String nome, Pageable page) {
    return repository.findByNomeContaining(nome, page);
  }
}
