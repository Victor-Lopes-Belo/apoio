package com.plataformaapoio.crianca.controller;

import com.plataformaapoio.crianca.model.Crianca;
import com.plataformaapoio.crianca.service.CriancaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/criancas")
public class CriancaController {

    private final CriancaService criancaService;

    public CriancaController(CriancaService criancaService) {
        this.criancaService = criancaService;
    }

    @PostMapping
    public ResponseEntity<Crianca> cadastrar(@PathVariable Long instituicaoId, @RequestBody Crianca crianca) {
        return ResponseEntity.ok(criancaService.salvar(instituicaoId, crianca));
    }

    @GetMapping
    public ResponseEntity<List<Crianca>> listarTodas() {
        return ResponseEntity.ok(criancaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Crianca> buscarPorId(@PathVariable Long id) {
        return criancaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Crianca> atualizar(@PathVariable Long id, @RequestBody Crianca crianca) {
        return ResponseEntity.ok(criancaService.atualizar(id, crianca));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        criancaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}