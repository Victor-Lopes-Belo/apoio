package com.Voluntarios.service;

import org.springframework.stereotype.Service;

import com.Voluntarios.model.Voluntario;
import com.Voluntarios.repository.VoluntarioRepository;

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
}
