package com.eventos.service;

import com.eventos.model.Local;
import com.eventos.repository.LocalRepository;
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

    public Local atualizar(Long id, Local dados) {
        return repository.findById(id).map(l -> {
            l.setNome(dados.getNome());
            l.setEndereco(dados.getEndereco());
            l.setCidade(dados.getCidade());
            return repository.save(l);
        }).orElseThrow(() -> new RuntimeException("Local não encontrado"));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
