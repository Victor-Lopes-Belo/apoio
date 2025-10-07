package com.plataformaapoio.ong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plataformaapoio.ong.model.Doacao;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
}
