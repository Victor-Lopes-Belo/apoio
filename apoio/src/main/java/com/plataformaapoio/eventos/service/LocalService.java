package com.plataformaapoio.eventos.service;

import com.plataformaapoio.eventos.model.Local;
import com.plataformaapoio.eventos.repository.LocalRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalService {

    private final LocalRepository repository;

    public LocalService(LocalRepository repository) {
        this.repository = repository;
    }

    public Local salvar(Local local) {
        return repository.save(local);
    }

    public List<Local> listar() {
        return repository.findAll();
    }

    public Optional<Local> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Optional<Local> atualizar(Long id, Local localAtualizado) {
        return repository.findById(id).map(local -> {
            local.setNome(localAtualizado.getNome());
            local.setEndereco(localAtualizado.getEndereco());
            local.setCidade(localAtualizado.getCidade());
            return repository.save(local);
        });
    }

    public boolean deletar(Long id) {
        return repository.findById(id).map(local -> {
            repository.delete(local);
            return true;
        }).orElse(false);
    }
}
