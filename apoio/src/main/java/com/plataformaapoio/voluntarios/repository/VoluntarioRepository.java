package com.plataformaapoio.voluntarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plataformaapoio.voluntarios.model.Voluntario;

@Repository
public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {
}
