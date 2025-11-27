package com.plataformaapoio.eventos.service;

import com.plataformaapoio.eventos.model.Convidado;
import com.plataformaapoio.eventos.model.Evento;
import com.plataformaapoio.eventos.model.Local;
import com.plataformaapoio.eventos.repository.ConvidadoRepository;
import com.plataformaapoio.eventos.repository.EventoRepository;
import com.plataformaapoio.eventos.repository.LocalRepository;

import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private final EventoRepository eventoRepo;
    private final LocalRepository localRepo;
    @Autowired
    private ConvidadoRepository convidadoRepo;

    public EventoService(EventoRepository eventoRepo, LocalRepository localRepo) {
        this.eventoRepo = eventoRepo;
        this.localRepo = localRepo;
    }

    public Evento salvar(Evento evento, Long localId) {
        Local local = localRepo.findById(localId)
                .orElseThrow(() -> new RuntimeException("Local não encontrado"));

        evento.setLocal(local);
        return eventoRepo.save(evento);
    }

    public List<Evento> listar() {
        return eventoRepo.findAll();
    }

    public Optional<Evento> buscarPorId(Long id) {
        return eventoRepo.findById(id);
    }

    public Optional<Evento> atualizar(Long id, Evento atualizado, Long localId) {
        return eventoRepo.findById(id).map(evento -> {
            evento.setNome(atualizado.getNome());
            evento.setDescricao(atualizado.getDescricao());
            evento.setData(atualizado.getData());

            if (localId != null) {
                Local novoLocal = localRepo.findById(localId)
                        .orElseThrow(() -> new RuntimeException("Local não encontrado"));
                evento.setLocal(novoLocal);
            }

            return eventoRepo.save(evento);
        });
    }

    public boolean deletar(Long id) {
        return eventoRepo.findById(id).map(e -> {
            eventoRepo.delete(e);
            return true;
        }).orElse(false);
    }

    public List<Evento> filtrarPorData(LocalDate data) {
        return eventoRepo.findByData(data);
    }

    public List<Evento> filtrarPorLocal(String nome) {
        return eventoRepo.findByLocal_NomeContainingIgnoreCase(nome);
    }

    public Optional<Evento> adicionarConvidado(Long eventoId, Long convidadoId) {
        Optional<Evento> eventoOpt = eventoRepo.findById(eventoId);
        Optional<Convidado> convidadoOpt = convidadoRepo.findById(convidadoId);

        if (eventoOpt.isEmpty() || convidadoOpt.isEmpty()) {
            return Optional.empty();
        }

        Evento evento = eventoOpt.get();
        Convidado convidado = convidadoOpt.get();

        evento.getConvidados().add(convidado);
        convidado.getEventos().add(evento);

        return Optional.of(eventoRepo.save(evento));
    }

    // LISTAR CONVIDADOS DO EVENTO
    public Optional<Set<Convidado>> listarConvidados(Long eventoId) {
        return eventoRepo.findById(eventoId).map(Evento::getConvidados);
    }

    // REMOVER CONVIDADO DO EVENTO
    public Optional<Evento> removerConvidado(Long eventoId, Long convidadoId) {
        Optional<Evento> eventoOpt = eventoRepo.findById(eventoId);
        Optional<Convidado> convidadoOpt = convidadoRepo.findById(convidadoId);

        if (eventoOpt.isEmpty() || convidadoOpt.isEmpty()) {
            return Optional.empty();
        }

        Evento evento = eventoOpt.get();
        Convidado convidado = convidadoOpt.get();

        evento.getConvidados().remove(convidado);
        convidado.getEventos().remove(evento);

        return Optional.of(eventoRepo.save(evento));
    }
}
