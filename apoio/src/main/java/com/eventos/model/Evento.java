package com.eventos.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    private String descricao;

    // muitos eventos para um local (N:1)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "local_id", nullable = false)
    private Local local;

    public Evento() {}

    public Evento(String nome, LocalDate data, String descricao, Local local) {
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;
        this.local = local;
    }

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Local getLocal() { return local; }
    public void setLocal(Local local) { this.local = local; }
}
