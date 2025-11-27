package com.plataformaapoio.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plataformaapoio.eventos.model.Convidado;

@Repository
public interface ConvidadoRepository extends JpaRepository<Convidado, Long> {
}
