package com.plataformaapoio.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plataformaapoio.eventos.model.Evento;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    // filtrar por data exata
    List<Evento> findByData(LocalDate data);

    // filtrar por nome do local (join implícito)
    List<Evento> findByLocal_NomeContainingIgnoreCase(String nomeLocal);
}
