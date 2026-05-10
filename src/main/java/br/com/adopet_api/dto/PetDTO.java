package br.com.adopet_api.dto;

import br.com.adopet_api.model.Pet;
import br.com.adopet_api.model.TipoPet;

public record PetDTO(Long id, String nome, Integer idade, TipoPet tipo, Boolean adotado, String imagem) {

    public PetDTO(Pet pet){

        this(pet.getId(), pet.getNome(), pet.getIdade(), pet.getTipo(), pet.getAdotado(), pet.getImagem());
    }
}
