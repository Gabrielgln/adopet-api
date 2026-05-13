package br.com.adopet_api.validator;

import br.com.adopet_api.dto.SolicitacaoDeAdocaoDTO;
import br.com.adopet_api.exception.AdocaoException;
import br.com.adopet_api.model.Pet;
import br.com.adopet_api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PetDisponivelValidator implements AdocaoValidator{
    @Autowired
    private PetRepository petRepository;

    @Override
    public void validate(SolicitacaoDeAdocaoDTO dto) {
        Pet pet = petRepository.getReferenceById(dto.idPet());
        if (pet.getAdotado()) {
            throw new AdocaoException("Pet já foi adotado!");
        }
    }
}
