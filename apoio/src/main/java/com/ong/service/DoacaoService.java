package com.ong.service;

import com.ong.model.Doacao;
import com.ong.repository.DoacaoRepository;
import org.springframework.stereotype.Service;

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
