package com.plataformadeeventos.cadastro_convidado.service;

import com.plataformadeeventos.cadastro_convidado.model.Evento;
import com.plataformadeeventos.cadastro_convidado.repository.EventoRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public Evento salvar(Evento evento) {
        return eventoRepository.save(evento);
    }

    public List<Evento> listar() {
        return eventoRepository.findAll();
    }

    public Evento buscarPorId(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }
}
