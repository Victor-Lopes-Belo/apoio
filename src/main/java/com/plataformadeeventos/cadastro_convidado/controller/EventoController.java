package com.plataformadeeventos.cadastro_convidado.controller;

import com.plataformadeeventos.cadastro_convidado.model.Evento;
import com.plataformadeeventos.cadastro_convidado.service.EventoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping
    public Evento criar(@RequestBody Evento evento) {
        return eventoService.salvar(evento);
    }

    @GetMapping
    public List<Evento> listar() {
        return eventoService.listar();
    }

    @GetMapping("/{id}")
    public Evento buscar(@PathVariable Long id) {
        return eventoService.buscarPorId(id);
    }
}

