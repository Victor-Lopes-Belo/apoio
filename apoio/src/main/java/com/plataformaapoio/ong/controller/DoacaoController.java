package com.plataformaapoio.ong.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.plataformaapoio.ong.model.Doacao;
import com.plataformaapoio.ong.service.DoacaoService;

import java.util.List;

@RestController
@RequestMapping("/doacoes")
public class DoacaoController {

    private final DoacaoService service;

    public DoacaoController(DoacaoService service) {
        this.service = service;
    }

    // POST - cadastrar doação
    @PostMapping
    public ResponseEntity<Doacao> criar(@RequestBody Doacao doacao) {
        return ResponseEntity.ok(service.salvar(doacao));
    }

    // GET - listar todas as doações
    @GetMapping
    public ResponseEntity<List<Doacao>> listar() {
        return ResponseEntity.ok(service.listar());
    }
}
