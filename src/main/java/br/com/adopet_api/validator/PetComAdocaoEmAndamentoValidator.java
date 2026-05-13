package br.com.adopet_api.validator;

import br.com.adopet_api.dto.SolicitacaoDeAdocaoDTO;
import br.com.adopet_api.exception.AdocaoException;
import br.com.adopet_api.model.StatusAdocao;
import br.com.adopet_api.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PetComAdocaoEmAndamentoValidator implements AdocaoValidator{
    @Autowired
    private AdocaoRepository adocaoRepository;


    @Override
    public void validate(SolicitacaoDeAdocaoDTO dto) {
        boolean petTemAdocaoEmAndamento = adocaoRepository
                .existsByPetIdAndStatus(dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO);

        if (petTemAdocaoEmAndamento) {
            throw new AdocaoException("Pet já está aguardando avaliação para ser adotado!");
        }
    }
}
