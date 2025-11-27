package com.plataformaapoio.eventos.controller;

import com.plataformaapoio.eventos.model.Convidado;
import com.plataformaapoio.eventos.model.Evento;
import com.plataformaapoio.eventos.service.EventoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService service;

    public EventoController(EventoService service) {
        this.service = service;
    }

    // ============================================
    // CRUD EVENTO
    // ============================================

    @Operation(summary = "Criar um novo evento")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Evento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Local não encontrado")
    })
    @PostMapping
    public ResponseEntity<Evento> criar(@RequestBody Evento evento,
            @RequestParam("localId") Long localId) {
        Evento novo = service.salvar(evento, localId);
        return ResponseEntity.created(URI.create("/eventos/" + novo.getId())).body(novo);
    }

    @Operation(summary = "Listar todos os eventos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de eventos retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<Evento>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(summary = "Buscar evento por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Evento encontrado"),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualizar um evento existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Evento atualizado"),
            @ApiResponse(responseCode = "404", description = "Evento ou Local não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizar(
            @PathVariable Long id,
            @RequestBody Evento evento,
            @RequestParam(value = "localId", required = false) Long localId) {

        return service.atualizar(id, evento, localId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar evento por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Evento deletado"),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!service.deletar(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    // ============================================
    // FILTROS
    // ============================================

    @Operation(summary = "Filtrar eventos por data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Eventos filtrados retornados com sucesso")
    })
    @GetMapping("/filtro/data")
    public ResponseEntity<List<Evento>> filtrarPorData(
            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return ResponseEntity.ok(service.filtrarPorData(data));
    }

    @Operation(summary = "Filtrar eventos por nome do local")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Eventos filtrados retornados com sucesso")
    })
    @GetMapping("/filtro/local")
    public ResponseEntity<List<Evento>> filtrarPorLocal(@RequestParam("nome") String nome) {
        return ResponseEntity.ok(service.filtrarPorLocal(nome));
    }

    // ============================================
    // RELACIONAMENTO EVENTO ↔ CONVIDADOS
    // ============================================

    @Operation(summary = "Adicionar convidado ao evento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Convidado adicionado"),
            @ApiResponse(responseCode = "404", description = "Evento ou Convidado não encontrado")
    })
    @PostMapping("/{eventoId}/convidados/{convidadoId}")
    public ResponseEntity<Evento> adicionarConvidado(
            @PathVariable Long eventoId,
            @PathVariable Long convidadoId) {

        return service.adicionarConvidado(eventoId, convidadoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar convidados de um evento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada"),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado")
    })
    @GetMapping("/{eventoId}/convidados")
    public ResponseEntity<Set<Convidado>> listarConvidados(@PathVariable Long eventoId) {

        return service.listarConvidados(eventoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Remover convidado do evento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Convidado removido"),
            @ApiResponse(responseCode = "404", description = "Evento ou Convidado não encontrado")
    })
    @DeleteMapping("/{eventoId}/convidados/{convidadoId}")
    public ResponseEntity<Evento> removerConvidado(
            @PathVariable Long eventoId,
            @PathVariable Long convidadoId) {

        return service.removerConvidado(eventoId, convidadoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
