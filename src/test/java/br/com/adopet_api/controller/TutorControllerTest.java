package br.com.adopet_api.controller;

import br.com.adopet_api.service.PetService;
import br.com.adopet_api.service.TutorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class TutorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private TutorService service;

    @Test
    void buscarTodos() throws Exception {
        var response = mockMvc.perform(
                get("/tutor")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void cadastrar() throws Exception {
        String json = """
                {
                    "nome": "Tutor teste",
                    "email": "teste@teste.com"
                }
                """;

        var response = mockMvc.perform(
                post("/tutor")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }
}