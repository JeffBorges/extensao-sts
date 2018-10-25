package br.edu.iftm.extensaosts.extensaosts.controller;

import br.edu.iftm.extensaosts.extensaosts.domain.Atividade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtividadeResourceTest {

  @Autowired
  private WebApplicationContext context;
  private ObjectMapper          objectMapper = new ObjectMapper();
  private MockMvc               mvc;
  private String                uri          = "/atividades/";

  @Before
  public void setup() {
    this.mvc = MockMvcBuilders.webAppContextSetup(this.context)
                              .build();
  }

  @Test
  public void testaId() throws Exception {
    this.mvc.perform(get(uri + "{id}", 1))
            .andExpect(status().isOk())
            .andExpect(jsonPath("nome", is("Visita Técnica GDG 2017")));
  }

  @Test
  public void testaFind() throws Exception {
    this.mvc.perform(get(uri + "?nome={nome}","Visita"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("totalElements", is(3)));
  }

  @Test
  public void testaSave() throws Exception {
    this.mvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                              .content(objectMapper.writeValueAsString(new Atividade("Visita Técnica GDG 2019"))))
            .andExpect(status().isOk())
            .andExpect(jsonPath("id", is(notNullValue())));
  }

  @Test
  public void testaUpdate() throws Exception {
    this.mvc.perform(put(uri + "{id}", 2).contentType(MediaType.APPLICATION_JSON)
                                         .content(objectMapper.writeValueAsString(new Atividade(2,"Visita Técnica GDG 2019"))))
            .andExpect(status().isOk())
            .andExpect(jsonPath("id", is(notNullValue())));
  }

  @Test
  public void testaUpdateError() throws Exception {
    this.mvc.perform(put(uri + "{id}", 6000).contentType(MediaType.APPLICATION_JSON)
                                            .content(objectMapper.writeValueAsString(new Atividade(2, "Visita Técnica GDG 2019"))))
            .andExpect(status().isNotFound());
  }

  @Test
  public void testaDelete() throws Exception {
    this.mvc.perform(delete(uri + "{id}", 3))
            .andExpect(status().isOk());
  }

  @Test
  public void testaDeleteError() throws Exception {
    this.mvc.perform(delete(uri + "{id}", 6000))
            .andExpect(status().isNotFound());
  }

  @Test
  public void testaNotFound() throws Exception {
    this.mvc.perform(get(uri + "{id}", 10))
            .andExpect(status().isNotFound());
  }
}
