package br.com.adopet_api.service;

import br.com.adopet_api.dto.*;
import br.com.adopet_api.exception.AdocaoException;
import br.com.adopet_api.model.Adocao;
import br.com.adopet_api.model.Pet;
import br.com.adopet_api.model.StatusAdocao;
import br.com.adopet_api.model.Tutor;
import br.com.adopet_api.repository.AdocaoRepository;
import br.com.adopet_api.repository.PetRepository;
import br.com.adopet_api.repository.TutorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdocaoService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private AdocaoRepository adocaoRepository;

    public List<AdocaoDTO> listarTodos(){

        return adocaoRepository.findAll().stream().map(AdocaoDTO::new).toList();
    }
    public AdocaoDTO listar(Long id){

        return adocaoRepository.findById(id).stream().findFirst().map(AdocaoDTO::new).orElse(null);
    }

    public void solicitar(SolicitacaoDeAdocaoDTO dto){
        Pet pet = petRepository.getReferenceById(dto.idPet());
        Tutor tutor = tutorRepository.getReferenceById(dto.idTutor());

        //Pet já adotadao
        if(pet.getAdotado()){
            throw new AdocaoException("Pet já adotado");
        }

        //Pet com solicitação de adoção em andamento
        Boolean petAdocaoEmAndamento = adocaoRepository.existsByPetIdAndStatus(dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO);
        if(petAdocaoEmAndamento){
            throw new AdocaoException("Pet com adoção em andamento");
        }

        //Tutor com 2 adoções aprovadas
        Integer tutorAdocoes = adocaoRepository.countByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO);
        if (tutorAdocoes == 2){
            throw new AdocaoException("Tutor com máximo de adoções");
        }

        adocaoRepository.save(new Adocao(tutor,pet, dto.motivo()));
    }

    public void aprovar(AprovarAdocaoDTO dto){
        Adocao adocao = adocaoRepository.getReferenceById(dto.idAdocao());
        adocao.marcarComoAprovada();
        adocao.getPet().marcarComoAdotado();
    }

    public void reprovar(ReprovarAdocaoDTO dto){
        Adocao adocao = adocaoRepository.getReferenceById(dto.idAdocao());
        adocao.marcarComoReprovada(dto.justificativa());
    }
}
