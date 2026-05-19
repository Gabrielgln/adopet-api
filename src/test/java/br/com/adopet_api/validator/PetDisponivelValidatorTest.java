package br.com.adopet_api.validator;

import br.com.adopet_api.dto.SolicitacaoDeAdocaoDTO;
import br.com.adopet_api.exception.AdocaoException;
import br.com.adopet_api.model.Pet;
import br.com.adopet_api.model.TipoPet;
import br.com.adopet_api.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetDisponivelValidatorTest {
    @Mock
    private PetRepository repository;
    @InjectMocks
    private PetDisponivelValidator validator;

    @Test
    void deveriaPermitirSolicitacaoDeAdocaoPet(){
        when(repository.getReferenceById(anyLong())).thenReturn(this.mockPet(false));
        assertDoesNotThrow(() -> validator.validate(this.mockSolicitacaoDeAdocaoDTO()));
    }

    @Test
    void deveriaNaoPermitirSolicitacaoDeAdocaoPet(){
        when(repository.getReferenceById(anyLong())).thenReturn(this.mockPet(true));
        AdocaoException ex = assertThrows(AdocaoException.class, () -> validator.validate(this.mockSolicitacaoDeAdocaoDTO()));
        assertEquals("Pet já foi adotado!", ex.getMessage());
    }

    private SolicitacaoDeAdocaoDTO mockSolicitacaoDeAdocaoDTO(){
        return new SolicitacaoDeAdocaoDTO(
                7L,
                2L,
                "Motivo qualquer"
        );
    }

    private Pet mockPet(boolean petAdotado){
        return new Pet(
                7L,
                "Pet teste",
                1,
                TipoPet.CACHORRO,
                petAdotado,
                "",
                null
        );
    }
}