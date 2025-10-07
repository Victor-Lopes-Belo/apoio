package com.plataformaapoio.ong.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "doacoes")
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // "financeira" ou "material"
    private Double valor; // se for financeira
    private String descricao; // se for material
    private LocalDate data;

    // Relacionamento com Instituição
    @ManyToOne
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;

    private String doador; // Nome do doador

    public Doacao() {}

    public Doacao(String tipo, Double valor, String descricao, LocalDate data, Instituicao instituicao, String doador) {
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.data = data;
        this.instituicao = instituicao;
        this.doador = doador;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public Instituicao getInstituicao() { return instituicao; }
    public void setInstituicao(Instituicao instituicao) { this.instituicao = instituicao; }

    public String getDoador() { return doador; }
    public void setDoador(String doador) { this.doador = doador; }
}
