package com.plataformadeeventos.cadastro_convidado.controller;

import com.plataformadeeventos.cadastro_convidado.model.Convidado;
import com.plataformadeeventos.cadastro_convidado.service.ConvidadoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/convidados")
public class ConvidadoController {

    private final ConvidadoService convidadoService;

    public ConvidadoController(ConvidadoService convidadoService) {
        this.convidadoService = convidadoService;
    }

    @PostMapping
    public Convidado criar(@RequestBody Convidado convidado) {
        return convidadoService.salvar(convidado);
    }

    @GetMapping
    public List<Convidado> listar() {
        return convidadoService.listar();
    }

    @GetMapping("/{id}")
    public Convidado buscar(@PathVariable Long id) {
        return convidadoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Convidado atualizar(@PathVariable Long id, @RequestBody Convidado convidado) {
        return convidadoService.atualizar(id, convidado);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        convidadoService.excluir(id);
    }

    @PostMapping("/{idConvidado}/evento/{idEvento}")
    public Convidado vincularEvento(@PathVariable Long idConvidado, @PathVariable Long idEvento) {
        return convidadoService.vincularEvento(idConvidado, idEvento);
    }
}

