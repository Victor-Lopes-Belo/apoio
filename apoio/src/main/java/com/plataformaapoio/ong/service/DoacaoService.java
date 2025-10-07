package com.plataformaapoio.ong.service;

import org.springframework.stereotype.Service;

import com.plataformaapoio.ong.model.Doacao;
import com.plataformaapoio.ong.repository.DoacaoRepository;

import java.util.List;

@Service
public class DoacaoService {

    private final DoacaoRepository repository;

    public DoacaoService(DoacaoRepository repository) {
        this.repository = repository;
    }

    public Doacao salvar(Doacao doacao) {
        return repository.save(doacao);
    }

    public List<Doacao> listar() {
        return repository.findAll();
    }

    public java.util.Optional<Doacao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Doacao atualizar(Long id, Doacao doacao) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Doação não encontrada");
        }
        doacao.setId(id);
        return repository.save(doacao);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Doação não encontrada");
        }
        repository.deleteById(id);
    }
}
