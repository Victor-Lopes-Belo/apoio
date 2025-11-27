package com.plataformaapoio.eventos.service;

import com.plataformaapoio.eventos.model.Convidado;
import com.plataformaapoio.eventos.repository.ConvidadoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConvidadoService {

    private final ConvidadoRepository repository;

    public ConvidadoService(ConvidadoRepository repository) {
        this.repository = repository;
    }

    public Convidado salvar(Convidado convidado) {
        return repository.save(convidado);
    }

    public List<Convidado> listar() {
        return repository.findAll();
    }

    public Optional<Convidado> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Optional<Convidado> atualizar(Long id, Convidado atualizado) {
        return repository.findById(id).map(convidado -> {
            convidado.setNome(atualizado.getNome());
            convidado.setEmail(atualizado.getEmail());
            return repository.save(convidado);
        });
    }

    public boolean deletar(Long id) {
        return repository.findById(id).map(c -> {
            repository.delete(c);
            return true;
        }).orElse(false);
    }
}
