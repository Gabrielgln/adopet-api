package br.com.adopet_api.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastroTutorDTO(
        @NotBlank
        String nome,

        @NotBlank
        String email
) {
}
