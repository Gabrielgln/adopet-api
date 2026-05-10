package br.com.adopet_api.repository;

import br.com.adopet_api.model.Adocao;
import br.com.adopet_api.model.StatusAdocao;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdocaoRepository extends JpaRepository<Adocao,Long> {

    Boolean existsByPetIdAndStatus(Long petId, StatusAdocao statusAdocao);

    Integer countByTutorIdAndStatus(@NotNull Long tutorId, StatusAdocao statusAdocao);
}
