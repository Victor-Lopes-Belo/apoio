package com.plataformaapoio.eventos.service;

import org.springframework.stereotype.Service;

import com.plataformaapoio.eventos.model.Evento;
import com.plataformaapoio.eventos.model.Local;
import com.plataformaapoio.eventos.repository.EventoRepository;
import com.plataformaapoio.eventos.repository.LocalRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final LocalRepository localRepository;

    public EventoService(EventoRepository eventoRepository, LocalRepository localRepository) {
        this.eventoRepository = eventoRepository;
        this.localRepository = localRepository;
    }

    public Evento salvar(Evento evento, Long localId) {
        Local local = localRepository.findById(localId)
                .orElseThrow(() -> new RuntimeException("Local não encontrado com id " + localId));
        evento.setLocal(local);
        return eventoRepository.save(evento);
    }

    public List<Evento> listar() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> buscarPorId(Long id) {
        return eventoRepository.findById(id);
    }

    public Evento atualizar(Long id, Evento dados, Long localId) {
        return eventoRepository.findById(id).map(e -> {
            e.setNome(dados.getNome());
            e.setData(dados.getData());
            e.setDescricao(dados.getDescricao());
            if (localId != null) {
                Local local = localRepository.findById(localId)
                        .orElseThrow(() -> new RuntimeException("Local não encontrado com id " + localId));
                e.setLocal(local);
            }
            return eventoRepository.save(e);
        }).orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }

    public void deletar(Long id) {
        eventoRepository.deleteById(id);
    }

    public List<Evento> filtrarPorData(LocalDate data) {
        return eventoRepository.findByData(data);
    }

    public List<Evento> filtrarPorLocal(String nomeLocal) {
        return eventoRepository.findByLocal_NomeContainingIgnoreCase(nomeLocal);
    }
}
