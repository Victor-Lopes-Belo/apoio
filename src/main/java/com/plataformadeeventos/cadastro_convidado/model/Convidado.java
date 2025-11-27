package com.plataformadeeventos.cadastro_convidado.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Convidado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    @ManyToMany
    @JoinTable(
            name = "convidado_evento",
            joinColumns = @JoinColumn(name = "convidado_id"),
            inverseJoinColumns = @JoinColumn(name = "evento_id")
    )
    private Set<Evento> eventos = new HashSet<>();
}
