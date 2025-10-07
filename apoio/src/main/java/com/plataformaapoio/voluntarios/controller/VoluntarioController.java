package com.plataformaapoio.voluntarios.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.plataformaapoio.voluntarios.model.Voluntario;
import com.plataformaapoio.voluntarios.service.VoluntarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/voluntarios")
public class VoluntarioController {

    private final VoluntarioService service;

    public VoluntarioController(VoluntarioService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar um novo voluntário")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Voluntário cadastrado com sucesso"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<Voluntario> cadastrar(@RequestBody Voluntario voluntario) {
        Voluntario salvo = service.salvar(voluntario);
        return ResponseEntity.ok(salvo);
    }

    @Operation(summary = "Listar todos os voluntários")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de voluntários retornada com sucesso"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<Voluntario>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(summary = "Obter detalhes de um voluntário por ID")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Detalhes do voluntário retornados com sucesso"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Voluntário não encontrado"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Voluntario> obterPorId(@PathVariable Long id) {
        Voluntario voluntario = service.obterPorId(id);
        return voluntario != null ? ResponseEntity.ok(voluntario) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Atualizar um voluntário existente")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Voluntário atualizado com sucesso"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Voluntário não encontrado"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Voluntario> atualizar(@PathVariable Long id, @RequestBody Voluntario voluntario) {
        Voluntario atualizado = service.atualizar(id, voluntario);
        return atualizado != null ? ResponseEntity.ok(atualizado) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Deletar um voluntário por ID")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Voluntário deletado com sucesso"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Voluntário não encontrado"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean removido = service.deletar(id);
        return removido ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
