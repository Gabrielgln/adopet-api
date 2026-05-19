package br.com.adopet_api.service;

import br.com.adopet_api.dto.CadastroPetDTO;
import br.com.adopet_api.model.Pet;
import br.com.adopet_api.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {
    @InjectMocks
    private PetService petService;
    @Mock
    private PetRepository repository;
    @Mock
    private ImageStorageService imagemService;
    @Mock
    private CadastroPetDTO dto;
    @Mock
    private MultipartFile file;

    @Test
    void listarTodos() {
        petService.listarTodos();
        then(repository).should().findAll();
    }

    @Test
    void cadastrar() {
        String uuid = UUID.randomUUID().toString();
        when(imagemService.upload(any())).thenReturn(uuid);
        petService.cadastrar(dto, file);
        verify(repository, times(1)).save(new Pet(dto, uuid));
    }
}