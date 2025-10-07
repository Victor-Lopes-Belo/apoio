package com.plataformaapoio.voluntarios.service;

import org.springframework.stereotype.Service;

import com.plataformaapoio.voluntarios.model.Voluntario;
import com.plataformaapoio.voluntarios.repository.VoluntarioRepository;

import java.util.List;

@Service
public class VoluntarioService {

    private final VoluntarioRepository repository;

    public VoluntarioService(VoluntarioRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Voluntario salvar(Voluntario voluntario) {
        return repository.save(voluntario);
    }

    // READ
    public List<Voluntario> listar() {
        return repository.findAll();
    }

    public Voluntario obterPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    // UPDATE
    public Voluntario atualizar(Long id, Voluntario voluntarioAtualizado) {
        return repository.findById(id).map(voluntario -> {
            voluntario.setNome(voluntarioAtualizado.getNome());
            voluntario.setDisponibilidade(voluntarioAtualizado.getDisponibilidade());
            voluntario.setAreaAtuacao(voluntarioAtualizado.getAreaAtuacao());
            return repository.save(voluntario);
        }).orElse(null);
    }  

    // DELETE
    public boolean deletar(Long id) {
        return repository.findById(id).map(voluntario -> {
            repository.delete(voluntario);
            return true;
        }).orElse(false);
    }
}
