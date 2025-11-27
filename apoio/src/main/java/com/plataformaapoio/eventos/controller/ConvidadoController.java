package com.plataformaapoio.eventos.controller;

import com.plataformaapoio.eventos.model.Convidado;
import com.plataformaapoio.eventos.service.ConvidadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/convidados")
public class ConvidadoController {

    private final ConvidadoService service;

    public ConvidadoController(ConvidadoService service) {
        this.service = service;
    }

    @Operation(summary = "Criar um novo convidado")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Convidado criado com sucesso")
    })
    @PostMapping
    public ResponseEntity<Convidado> criar(@RequestBody Convidado convidado) {
        Convidado novo = service.salvar(convidado);
        return ResponseEntity.created(URI.create("/convidados/" + novo.getId())).body(novo);
    }

    @Operation(summary = "Listar todos os convidados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<Convidado>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(summary = "Buscar convidado por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Convidado encontrado"),
            @ApiResponse(responseCode = "404", description = "Convidado não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Convidado> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualizar um convidado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Convidado atualizado"),
            @ApiResponse(responseCode = "404", description = "Convidado não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Convidado> atualizar(@PathVariable Long id, @RequestBody Convidado convidado) {
        return service.atualizar(id, convidado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar convidado por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Convidado deletado"),
            @ApiResponse(responseCode = "404", description = "Convidado não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!service.deletar(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
