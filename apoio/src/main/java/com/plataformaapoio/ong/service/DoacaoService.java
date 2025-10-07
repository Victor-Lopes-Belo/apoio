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
}
