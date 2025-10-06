package com.plataformaapoio.cadastro_usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.plataformaapoio.cadastro_usuario.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);
}
