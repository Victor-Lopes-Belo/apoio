package com.plataformadeeventos.cadastro_convidado.repository;

import com.plataformadeeventos.cadastro_convidado.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> { }
