package com.eventos.repository;

import com.eventos.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByData(LocalDate data);
    List<Evento> findByLocal_NomeContainingIgnoreCase(String localNome);
}
