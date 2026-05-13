package br.com.adopet_api.service;

import br.com.adopet_api.dto.AdocaoDTO;
import br.com.adopet_api.dto.AprovarAdocaoDTO;
import br.com.adopet_api.dto.ReprovarAdocaoDTO;
import br.com.adopet_api.dto.SolicitacaoDeAdocaoDTO;
import br.com.adopet_api.model.Adocao;
import br.com.adopet_api.model.Pet;
import br.com.adopet_api.model.Tutor;
import br.com.adopet_api.repository.AdocaoRepository;
import br.com.adopet_api.repository.PetRepository;
import br.com.adopet_api.repository.TutorRepository;
import br.com.adopet_api.validator.AdocaoValidator;
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
    @Autowired
    private List<AdocaoValidator> validators;

    public List<AdocaoDTO> listarTodos(){

        return adocaoRepository.findAll().stream().map(AdocaoDTO::new).toList();
    }
    public AdocaoDTO listar(Long id){

        return adocaoRepository.findById(id).stream().findFirst().map(AdocaoDTO::new).orElse(null);
    }

    public void solicitar(SolicitacaoDeAdocaoDTO dto){
        Pet pet = petRepository.getReferenceById(dto.idPet());
        Tutor tutor = tutorRepository.getReferenceById(dto.idTutor());

        validators.forEach(v -> v.validate(dto));

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
