package br.com.adopet_api.validator;

import br.com.adopet_api.dto.SolicitacaoDeAdocaoDTO;
import br.com.adopet_api.exception.AdocaoException;
import br.com.adopet_api.model.StatusAdocao;
import br.com.adopet_api.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TutorComLimiteDeAdocoesValidator implements AdocaoValidator{
    @Autowired
    private AdocaoRepository adocaoRepository;

    @Override
    public void validate(SolicitacaoDeAdocaoDTO dto) {
        int totalDeAdocoesPorTutor = adocaoRepository.countByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO);

        if(totalDeAdocoesPorTutor == 2)
            throw new AdocaoException("Tutor chegou ao limite máximo de 2 adoções!");
    }
}