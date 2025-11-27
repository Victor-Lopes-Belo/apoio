package com.plataformaapoio.eventos.controller;

import com.plataformaapoio.eventos.model.Local;
import com.plataformaapoio.eventos.service.LocalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/locais")
public class LocalController {

    private final LocalService service;

    public LocalController(LocalService service) {
        this.service = service;
    }

    @Operation(summary = "Criar um novo local")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Local criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping(consumes = { "application/json", "application/json;charset=UTF-8" })
    public ResponseEntity<Local> criar(@RequestBody Local local) {
        Local novo = service.salvar(local);
        return ResponseEntity.created(URI.create("/locais/" + novo.getId())).body(novo);
    }

    @Operation(summary = "Listar todos os locais")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de locais retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<Local>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(summary = "Buscar local por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Local encontrado"),
            @ApiResponse(responseCode = "404", description = "Local não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Local> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualizar um local existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Local atualizado"),
            @ApiResponse(responseCode = "404", description = "Local não encontrado")
    })
    @PutMapping(value = "/{id}", consumes = { "application/json", "application/json;charset=UTF-8" })
    public ResponseEntity<Local> atualizar(@PathVariable Long id, @RequestBody Local local) {
        return service.atualizar(id, local)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar um local por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Local deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Local não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!service.deletar(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}