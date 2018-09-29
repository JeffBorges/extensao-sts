package br.edu.iftm.extensaosts.extensaosts;

import br.edu.iftm.extensaosts.extensaosts.domain.Atividade;
import br.edu.iftm.extensaosts.extensaosts.repositories.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExtensaoStsApplication implements CommandLineRunner {

  private final AtividadeRepository repository;

  @Autowired
  public ExtensaoStsApplication(AtividadeRepository repository) {
    this.repository = repository;
  }

  public static void main(String[] args) {
    SpringApplication.run(ExtensaoStsApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Atividade a = new Atividade();
    a.setNome("SiIMPOS");
    repository.save(a);
  }
}
