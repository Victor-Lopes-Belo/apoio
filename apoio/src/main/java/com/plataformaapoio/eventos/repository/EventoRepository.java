package com.plataformaapoio.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plataformaapoio.eventos.model.Evento;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByData(LocalDate data);
    List<Evento> findByLocal_NomeContainingIgnoreCase(String localNome);
}
