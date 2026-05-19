package br.com.adopet_api.validator;

import br.com.adopet_api.dto.SolicitacaoDeAdocaoDTO;
import br.com.adopet_api.exception.AdocaoException;
import br.com.adopet_api.model.StatusAdocao;
import br.com.adopet_api.repository.AdocaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TutorComLimiteDeAdocoesValidatorTest {
    @Mock
    private AdocaoRepository adocaoRepository;
    @Mock
    private SolicitacaoDeAdocaoDTO dto;
    @InjectMocks
    private TutorComLimiteDeAdocoesValidator validator;

    @Test
    void naoDeveriaPermitirSolicitacaoDeAdocaoTutorAtingiuLimiteDe2Adocoes(){
        when(adocaoRepository.countByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO)).thenReturn(2);
        assertThrows(AdocaoException.class, () -> validator.validate(dto));
    }

    @Test
    void deveriaPermitirSolicitacaoDeAdocaoTutorNaoAtingiuLimiteDe2Adocoes(){
        when(adocaoRepository.countByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO)).thenReturn(1);
        assertDoesNotThrow(() -> validator.validate(dto));
    }

}