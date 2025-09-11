package com.plataformaapoio.cadastro_usuario.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.plataformaapoio.cadastro_usuario.model.Usuario;
import com.plataformaapoio.cadastro_usuario.services.UsuarioServices;

@RestController
@RequestMapping("api/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioServices usuarioServices;

    @PostMapping
    public ResponseEntity<Void> salvarUsuario(@RequestBody Usuario usuario){
        usuarioServices.salvarUsuario(usuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(@RequestParam String email){
        return ResponseEntity.ok(usuarioServices.buscarUsuarioPorEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarUsuarioPorEmail(@RequestParam String email){
        usuarioServices.deletarUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarUsuarioPorId(@RequestParam Integer id,
                                                     @RequestBody Usuario usuario){
        usuarioServices.atualizarUsuarioPorId(id, usuario);
        return ResponseEntity.ok().build();
    }
}
