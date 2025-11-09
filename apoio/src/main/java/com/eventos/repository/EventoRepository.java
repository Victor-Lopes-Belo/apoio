package com.eventos.repository;

import com.eventos.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    // filtrar por data exata
    List<Evento> findByData(LocalDate data);

    // filtrar por nome do local (join implícito)
    List<Evento> findByLocal_NomeContainingIgnoreCase(String nomeLocal);
}
