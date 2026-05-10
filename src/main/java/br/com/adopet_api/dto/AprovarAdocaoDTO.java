package br.com.adopet_api.dto;

import jakarta.validation.constraints.NotNull;

public record AprovarAdocaoDTO(@NotNull Long idAdocao) {
}