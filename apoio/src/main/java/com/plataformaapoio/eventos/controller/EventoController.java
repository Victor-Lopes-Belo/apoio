package com.plataformaapoio.eventos.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.plataformaapoio.eventos.model.Evento;
import com.plataformaapoio.eventos.service.EventoService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService service;

    public EventoController(EventoService service) {
        this.service = service;
    }

    // criar: recebe evento no body e localId como request param
    @PostMapping
    public ResponseEntity<Evento> criar(@RequestBody Evento evento,
            @RequestParam("localId") Long localId) {
        return ResponseEntity.ok(service.salvar(evento, localId));
    }

    @GetMapping
    public ResponseEntity<List<Evento>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizar(@PathVariable Long id,
            @RequestBody Evento evento,
            @RequestParam(value = "localId", required = false) Long localId) {
        return ResponseEntity.ok(service.atualizar(id, evento, localId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // filtro por data: ?data=2025-11-09
    @GetMapping("/filtro/data")
    public ResponseEntity<List<Evento>> filtrarPorData(
            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return ResponseEntity.ok(service.filtrarPorData(data));
    }

    // filtro por local: ?nome=Auditório
    @GetMapping("/filtro/local")
    public ResponseEntity<List<Evento>> filtrarPorLocal(@RequestParam("nome") String nome) {
        return ResponseEntity.ok(service.filtrarPorLocal(nome));
    }
}
