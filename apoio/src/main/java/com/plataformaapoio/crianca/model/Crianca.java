package com.plataformaapoio.crianca.model;

import com.plataformaapoio.instituicao.model.Instituicao; // Importe a classe Instituicao
import jakarta.persistence.*;

@Entity
public class Crianca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Long idade;

    // --- CORREÇÃO APLICADA AQUI ---
    @ManyToOne
    @JoinColumn(name = "instituicao_id", nullable = false) // Coluna de junção correta
    private Instituicao instituicao; // Relacionamento correto

    public Crianca() {
    }

    // Construtor atualizado
    public Crianca(String nome, Long idade, Instituicao instituicao) {
        this.nome = nome;
        this.idade = idade;
        this.instituicao = instituicao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdade() {
        return idade;
    }

    public void setIdade(Long idade) {
        this.idade = idade;
    }

    // Getter e Setter atualizados
    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }
}