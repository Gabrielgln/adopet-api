package br.com.adopet_api.service;

import br.com.adopet_api.dto.CadastroTutorDTO;
import br.com.adopet_api.dto.TutorDTO;
import br.com.adopet_api.model.Tutor;
import br.com.adopet_api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    public List<TutorDTO> listarTodos(){
        return repository.findAll().stream().map(TutorDTO::new).toList();
    }
    public void cadastrar(CadastroTutorDTO dados){
        repository.save(new Tutor(dados));
    }
}
