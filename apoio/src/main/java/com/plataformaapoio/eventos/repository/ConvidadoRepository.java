package com.plataformaapoio.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.plataformaapoio.eventos.model.Convidado;

public interface ConvidadoRepository extends JpaRepository<Convidado, Long> {
}
