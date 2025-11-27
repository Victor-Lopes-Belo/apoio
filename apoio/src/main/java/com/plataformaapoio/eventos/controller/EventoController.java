package com.plataformaapoio.eventos.controller;

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

    @GetMapping
    public ResponseEntity<List<Evento>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Evento> cadastrar(@RequestBody Evento evento) {
        return ResponseEntity.ok(service.salvar(evento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizar(@PathVariable Long id, @RequestBody Evento evento) {
        return ResponseEntity.ok(service.atualizar(id, evento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtro/data")
    public ResponseEntity<List<Evento>> filtrarPorData(@RequestParam("data") String data) {
        LocalDate localDate = LocalDate.parse(data);
        return ResponseEntity.ok(service.filtrarPorData(localDate));
    }

    @GetMapping("/filtro/local")
    public ResponseEntity<List<Evento>> filtrarPorLocal(@RequestParam("nome") String nome) {
        return ResponseEntity.ok(service.filtrarPorLocal(nome));
    }
}
