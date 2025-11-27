package com.plataformaapoio.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.plataformaapoio.eventos.model.Local;

public interface LocalRepository extends JpaRepository<Local, Long> {
}
