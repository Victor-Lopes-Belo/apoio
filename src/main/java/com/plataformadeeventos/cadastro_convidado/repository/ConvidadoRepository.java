package com.plataformadeeventos.cadastro_convidado.repository;

import com.plataformadeeventos.cadastro_convidado.model.Convidado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvidadoRepository extends JpaRepository<Convidado, Long> { }
