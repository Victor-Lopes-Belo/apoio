package com.plataformadeeventos.cadastro_convidado.service;

import com.plataformadeeventos.cadastro_convidado.model.Convidado;
import com.plataformadeeventos.cadastro_convidado.model.Evento;
import com.plataformadeeventos.cadastro_convidado.repository.ConvidadoRepository;
import com.plataformadeeventos.cadastro_convidado.repository.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final ConvidadoRepository convidadoRepository;

    public EventoService(EventoRepository eventoRepository, ConvidadoRepository convidadoRepository) {
        this.eventoRepository = eventoRepository;
        this.convidadoRepository = convidadoRepository;
    }

    public Evento salvar(Evento evento) {
        return eventoRepository.save(evento);
    }

    public List<Evento> listar() {
        return eventoRepository.findAll();
    }

    public Evento buscarPorId(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }

    // 🔥 MÉTODO QUE FALTAVA PARA O SEU ENDPOINT FUNCIONAR
    public Evento adicionarConvidado(Long eventoId, Long convidadoId) {

        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        Convidado convidado = convidadoRepository.findById(convidadoId)
                .orElseThrow(() -> new RuntimeException("Convidado não encontrado"));

        evento.getConvidados().add(convidado);

        return eventoRepository.save(evento);
    }
}
