package com.plataformaapoio.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plataformaapoio.eventos.model.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {
}
