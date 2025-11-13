package com.plataformaapoio.eventos.service;

import org.springframework.stereotype.Service;

import com.plataformaapoio.eventos.model.Evento;
import com.plataformaapoio.eventos.repository.EventoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private final EventoRepository repository;

    public EventoService(EventoRepository repository) {
        this.repository = repository;
    }

    public List<Evento> listar() {
        return repository.findAll();
    }

    public Optional<Evento> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Evento salvar(Evento evento) {
        return repository.save(evento);
    }

    public Evento atualizar(Long id, Evento eventoAtualizado) {
        return repository.findById(id).map(evento -> {
            evento.setNome(eventoAtualizado.getNome());
            evento.setData(eventoAtualizado.getData());
            evento.setDescricao(eventoAtualizado.getDescricao());
            evento.setLocal(eventoAtualizado.getLocal());
            evento.setConvidados(eventoAtualizado.getConvidados());
            return repository.save(evento);
        }).orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public List<Evento> filtrarPorData(LocalDate data) {
        return repository.findByData(data);
    }

    public List<Evento> filtrarPorLocal(String nomeLocal) {
        return repository.findByLocal_NomeContainingIgnoreCase(nomeLocal);
    }
}
