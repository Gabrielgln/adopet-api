package br.com.adopet_api.dto;

import br.com.adopet_api.model.Tutor;

public record TutorDTO(Long id, String nome, String email) {

    public TutorDTO(Tutor tutor){
        this(tutor.getId(), tutor.getNome(), tutor.getEmail());
    }
}
