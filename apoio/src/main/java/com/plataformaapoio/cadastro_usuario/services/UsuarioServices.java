package com.plataformaapoio.cadastro_usuario.services;

import org.springframework.stereotype.Service;

import com.plataformaapoio.cadastro_usuario.model.Usuario;
import com.plataformaapoio.cadastro_usuario.repository.UsuarioRepository;

@Service
public class UsuarioServices {

    private final UsuarioRepository repository;

    public UsuarioServices(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario){
        repository.saveAndFlush(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email){

        return repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado")
        );
    }

    public void deletarUsuarioPorEmail(String email){
        repository.deleteByEmail(email);
    }

    public void atualizarUsuarioPorId(Integer id, Usuario usuario){
        Usuario usuarioEntity = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Usuario não encontrado"));
        Usuario usuarioAtualizado = Usuario.builder()
                .email(usuario.getEmail() != null ?
                        usuario.getEmail() : usuarioEntity.getEmail())
                .nome(usuario.getNome() != null ? usuario.getNome():
                        usuarioEntity.getNome())
                .senha(usuario.getSenha() != null ? usuario.getSenha():
                        usuarioEntity.getSenha())
                .tipoPerfil(usuario.getTipoPerfil() != null ? usuario.getTipoPerfil():
                        usuarioEntity.getTipoPerfil())

                .id(usuarioEntity.getId())
                .build();
        repository.saveAndFlush(usuarioAtualizado);
    }
}
