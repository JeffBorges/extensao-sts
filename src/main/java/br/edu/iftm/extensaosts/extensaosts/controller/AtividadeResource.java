package br.edu.iftm.extensaosts.extensaosts.controller;

import br.edu.iftm.extensaosts.extensaosts.domain.Atividade;
import br.edu.iftm.extensaosts.extensaosts.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/atividades")
public class AtividadeResource {

  private final AtividadeService service;

  @Autowired
  public AtividadeResource(AtividadeService service) {
    this.service = service;
  }

  @GetMapping(value = "/{id:[0-9.,]*$}")
  public ResponseEntity<Atividade> findById(@PathVariable Integer id) {
    return service.buscar(id)
                  .map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound()
                                        .build());
  }

  @PostMapping
  public ResponseEntity<Atividade> save(@Valid @RequestBody Atividade atividade) {
    return ResponseEntity.ok(service.salvar(atividade));
  }

  @PutMapping("/{id:[0-9.,]*$}")
  public ResponseEntity<Atividade> update(@PathVariable("id") Integer id, @Valid @RequestBody Atividade atividade) {
    if (!service.buscar(id)
                .isPresent())
      return ResponseEntity.notFound()
                           .build();
    atividade.setId(id);
    return ResponseEntity.ok(service.salvar(atividade));
  }

  @DeleteMapping("/{id:[0-9.,]*$}")
  public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
    if (!service.buscar(id)
                .isPresent())
      return ResponseEntity.notFound()
                           .build();

    service.deletar(id);
    return (ResponseEntity<?>) ResponseEntity.ok()
                                             .build();
  }

  @GetMapping
  public ResponseEntity<Page<Atividade>> find(@RequestParam(value = "nome") String nome, @RequestParam(value = "page", required = false) Integer page,
                                              @RequestParam(value = "size", required = false) Integer size,
                                              @RequestParam(value = "direction", required = false) String direction,
                                              @RequestParam(value = "field", required = false) String field) {

    Pageable pageable = Pageable.unpaged();
    Sort sort = null;
    if (field != null)
      sort = Sort.Direction.fromOptionalString(direction)
                           .map(d -> Sort.by(d, field))
                           .orElse(null);

    if (size != null && size > 0 && page != null && page >= 0)
      pageable = PageRequest.of(page, size, sort);

    return ResponseEntity.ok(service.findByName(nome, pageable));
  }
}
