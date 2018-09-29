package br.edu.iftm.extensaosts.extensaosts.service;

import br.edu.iftm.extensaosts.extensaosts.domain.Atividade;
import br.edu.iftm.extensaosts.extensaosts.repositories.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtividadeService {

  private final AtividadeRepository repository;

  @Autowired
  public AtividadeService(AtividadeRepository repository) {
    this.repository = repository;
  }

  public Atividade buscar(Integer id) {
    return repository.getOne(id);
  }
}
