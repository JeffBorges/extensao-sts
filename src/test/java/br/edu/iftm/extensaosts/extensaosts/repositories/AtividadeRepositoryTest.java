package br.edu.iftm.extensaosts.extensaosts.repositories;

import br.edu.iftm.extensaosts.extensaosts.domain.Atividade;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtividadeRepositoryTest {

  @Autowired
  private AtividadeRepository repo;

  @Test
  public void verificaQuantidadeAtividadesCadastradas() {
    Page<Atividade> atividades = this.repo.findAll(PageRequest.of(0, 10));
    assertThat(atividades.getTotalElements()).isGreaterThan(1L);
  }

  @Test
  public void findByName() {
    List<Atividade> atividades = repo.findByNomeIgnoreCaseContaining("gdg");
    assertThat(atividades.size()).isGreaterThan(1);
  }

  @Test
  public void find() {
    List<Atividade> atividade =  repo.findByNomeIgnoreCaseContaining("jeff");
    assertThat(atividade.size()).isEqualTo(0);
  }
}
