package br.com.adopet_api.service;

import br.com.adopet_api.dto.CadastroTutorDTO;
import br.com.adopet_api.model.Tutor;
import br.com.adopet_api.repository.TutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class TutorServiceTest {
    @InjectMocks
    private TutorService service;
    @Mock
    private TutorRepository repository;
    @Mock
    private CadastroTutorDTO dto;

    @Test
    void listarTodos() {
        service.listarTodos();
        then(repository).should().findAll();
    }

    @Test
    void cadastrar() {
        service.cadastrar(dto);
        then(repository).should().save(new Tutor(dto));
    }
}