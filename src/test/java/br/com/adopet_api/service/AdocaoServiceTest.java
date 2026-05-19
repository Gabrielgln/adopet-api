package br.com.adopet_api.service;

import br.com.adopet_api.dto.SolicitacaoDeAdocaoDTO;
import br.com.adopet_api.model.Adocao;
import br.com.adopet_api.model.Pet;
import br.com.adopet_api.model.Tutor;
import br.com.adopet_api.repository.AdocaoRepository;
import br.com.adopet_api.repository.PetRepository;
import br.com.adopet_api.repository.TutorRepository;
import br.com.adopet_api.validator.AdocaoValidator;
import br.com.adopet_api.validator.PetComAdocaoEmAndamentoValidator;
import br.com.adopet_api.validator.PetDisponivelValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AdocaoServiceTest {
    @Mock
    private PetRepository petRepository;
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private AdocaoRepository adocaoRepository;
    @Spy
    private List<AdocaoValidator> validators = new ArrayList<>();
    @Mock
    private PetDisponivelValidator validator1;
    @Mock
    private PetComAdocaoEmAndamentoValidator validator2;
    @InjectMocks
    private AdocaoService service;
    @Mock
    private Pet pet;
    @Mock
    private Tutor tutor;
    @Captor
    private ArgumentCaptor<Adocao> adocaoCaptor;

    @Test
    void deveriaSalvarAdocaoAoSolicitar(){
        SolicitacaoDeAdocaoDTO dto = this.mockSolicitacaoDeAdocaoDTO();

        when(petRepository.getReferenceById(dto.idPet())).thenReturn(pet);
        when(tutorRepository.getReferenceById(dto.idTutor())).thenReturn(tutor);

        service.solicitar(dto);

        verify(adocaoRepository, times(1)).save(adocaoCaptor.capture());
        Adocao adocao = adocaoCaptor.getValue();
        Assertions.assertEquals(pet, adocao.getPet());
        Assertions.assertEquals(tutor, adocao.getTutor());
        Assertions.assertEquals(dto.motivo(), adocao.getMotivo());
    }

    @Test
    void deveriaChamarValidadoresAdocaoAoSolicitar(){
        SolicitacaoDeAdocaoDTO dto = this.mockSolicitacaoDeAdocaoDTO();

        given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        validators.add(validator1);
        validators.add(validator2);

        service.solicitar(dto);

        then(validator1).should().validate(dto);
        then(validator2).should().validate(dto);
    }

    private SolicitacaoDeAdocaoDTO mockSolicitacaoDeAdocaoDTO(){
        return new SolicitacaoDeAdocaoDTO(
                1L,
                1L,
                "Motivo qualquer"
        );
    }
}