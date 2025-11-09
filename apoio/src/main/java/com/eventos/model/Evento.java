package com.eventos.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private LocalDate data;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "local_id")
    private Local local;

    @ManyToMany
    @JoinTable(
        name = "evento_convidado",
        joinColumns = @JoinColumn(name = "evento_id"),
        inverseJoinColumns = @JoinColumn(name = "convidado_id")
    )
    private Set<Convidado> convidados = new HashSet<>();

    public Evento() {}

    public Evento(String nome, LocalDate data, String descricao, Local local) {
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;
        this.local = local;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public LocalDate getData() { return data; }
    public String getDescricao() { return descricao; }
    public Local getLocal() { return local; }
    public Set<Convidado> getConvidados() { return convidados; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setData(LocalDate data) { this.data = data; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setLocal(Local local) { this.local = local; }
    public void setConvidados(Set<Convidado> convidados) { this.convidados = convidados; }
}
