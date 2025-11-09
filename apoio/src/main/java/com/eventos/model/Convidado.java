package com.eventos.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "convidados")
public class Convidado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;

    @ManyToMany(mappedBy = "convidados")
    private Set<Evento> eventos = new HashSet<>();

    public Convidado() {}

    public Convidado(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public Set<Evento> getEventos() { return eventos; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setEventos(Set<Evento> eventos) { this.eventos = eventos; }
}
