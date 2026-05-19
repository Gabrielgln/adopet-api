package br.com.adopet_api.controller;

import br.com.adopet_api.service.PetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class PetControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private PetService service;

    @Test
    void buscarTodos() throws Exception {
        var response = mockMvc.perform(
                get("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void cadastrar() throws Exception {
        MockMultipartFile imagem =
                new MockMultipartFile(
                        "imagem",
                        "foto.png",
                        MediaType.IMAGE_PNG_VALUE,
                        "foto".getBytes()
                );

        MockMultipartFile dados =
                new MockMultipartFile(
                        "dados",
                        "",
                        MediaType.APPLICATION_JSON_VALUE,
                        """
                        {
                            "nome": "Caramelo",
                            "idade": 4,
                            "tipo": "CACHORRO",
                            "porte": "MEDIO"
                        }
                        """.getBytes()
                );

        var response = mockMvc.perform(
                multipart("/pets")
                        .file(imagem)
                        .file(dados)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }
}