package br.com.adopet_api.validator;

import br.com.adopet_api.dto.SolicitacaoDeAdocaoDTO;

public interface AdocaoValidator {
    void validate(SolicitacaoDeAdocaoDTO dto);
}