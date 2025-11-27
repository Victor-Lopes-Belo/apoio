package com.plataformadeeventos.cadastro_convidado.service;

import com.plataformadeeventos.cadastro_convidado.model.Convidado;
import com.plataformadeeventos.cadastro_convidado.model.Evento;
import com.plataformadeeventos.cadastro_convidado.repository.ConvidadoRepository;
import com.plataformadeeventos.cadastro_convidado.repository.EventoRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConvidadoService {

    private final ConvidadoRepository convidadoRepository;
    private final EventoRepository eventoRepository;

    public ConvidadoService(ConvidadoRepository convidadoRepository, EventoRepository eventoRepository) {
        this.convidadoRepository = convidadoRepository;
        this.eventoRepository = eventoRepository;
    }

    public Convidado salvar(Convidado convidado) {
        return convidadoRepository.save(convidado);
    }

    public List<Convidado> listar() {
        return convidadoRepository.findAll();
    }

    public Convidado buscarPorId(Long id) {
        return convidadoRepository.findById(id).orElse(null);
    }

    public Convidado atualizar(Long id, Convidado novo) {
        Convidado c = buscarPorId(id);
        if (c != null) {
            c.setNome(novo.getNome());
            c.setEmail(novo.getEmail());
            return convidadoRepository.save(c);
        }
        return null;
    }

    public void excluir(Long id) {
        convidadoRepository.deleteById(id);
    }

    public Convidado vincularEvento(Long convidadoId, Long eventoId) {
        Convidado convidado = buscarPorId(convidadoId);
        Evento evento = eventoRepository.findById(eventoId).orElse(null);

        if (convidado != null && evento != null) {
            convidado.getEventos().add(evento);
            return convidadoRepository.save(convidado);
        }
        return null;
    }
}
