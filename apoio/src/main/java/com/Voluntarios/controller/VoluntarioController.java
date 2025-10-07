package com.Voluntarios.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Voluntarios.model.Voluntario;
import com.Voluntarios.service.VoluntarioService;

import java.util.List;

@RestController
@RequestMapping("/voluntarios")
public class VoluntarioController {

    private final VoluntarioService service;

    public VoluntarioController(VoluntarioService service) {
        this.service = service;
    }

    // POST - Cadastrar voluntário
    @PostMapping
    public ResponseEntity<Voluntario> cadastrar(@RequestBody Voluntario voluntario) {
        Voluntario salvo = service.salvar(voluntario);
        return ResponseEntity.ok(salvo);
    }

    // GET - Listar voluntários
    @GetMapping
    public ResponseEntity<List<Voluntario>> listar() {
        return ResponseEntity.ok(service.listar());
    }
}
