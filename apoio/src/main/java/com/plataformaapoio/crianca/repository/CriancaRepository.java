package com.plataformaapoio.crianca.repository;

import com.plataformaapoio.crianca.model.Crianca;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CriancaRepository extends JpaRepository<Crianca, Long> {
    List<Crianca> findByInstituicaoId(Long instituicaoId);
}