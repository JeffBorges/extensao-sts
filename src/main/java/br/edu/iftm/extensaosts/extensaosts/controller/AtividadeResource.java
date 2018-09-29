package br.edu.iftm.extensaosts.extensaosts.controller;

import br.edu.iftm.extensaosts.extensaosts.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/atividades")
public class AtividadeResource {

  private final AtividadeService service;

  @Autowired
  public AtividadeResource(AtividadeService service) {
    this.service = service;
  }

  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  public ResponseEntity<?> find (@PathVariable Integer id) {
    return ResponseEntity.ok(service.buscar(id));
  }

}
