package com.Voluntarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Voluntarios.model.Voluntario;

@Repository
public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {
}
