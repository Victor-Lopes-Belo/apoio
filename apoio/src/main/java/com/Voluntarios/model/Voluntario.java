package com.Voluntarios.model;

import jakarta.persistence.*;

@Entity
@Table(name = "voluntarios")
public class Voluntario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String disponibilidade; // Exemplo: "Segunda a sexta, manhã"
    private String areaAtuacao; // Exemplo: "Educação", "Saúde", "Eventos"

    public Voluntario() {}

    public Voluntario(String nome, String disponibilidade, String areaAtuacao) {
        this.nome = nome;
        this.disponibilidade = disponibilidade;
        this.areaAtuacao = areaAtuacao;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDisponibilidade() { return disponibilidade; }
    public void setDisponibilidade(String disponibilidade) { this.disponibilidade = disponibilidade; }

    public String getAreaAtuacao() { return areaAtuacao; }
    public void setAreaAtuacao(String areaAtuacao) { this.areaAtuacao = areaAtuacao; }
}
