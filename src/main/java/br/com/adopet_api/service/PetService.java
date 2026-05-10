package br.com.adopet_api.service;

import br.com.adopet_api.dto.CadastroPetDTO;
import br.com.adopet_api.dto.PetDTO;
import br.com.adopet_api.model.Pet;
import br.com.adopet_api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository repository;

    @Autowired
    private ImageStorageService imagemService;

    public List<PetDTO> listarTodos(){
        return repository.findAll().stream().map(PetDTO::new).toList();
    }
    public void cadastrar(CadastroPetDTO dto, MultipartFile imagem){

        String nomeImagem = imagemService.upload(imagem);

        repository.save(new Pet(dto, nomeImagem));
    }
}
