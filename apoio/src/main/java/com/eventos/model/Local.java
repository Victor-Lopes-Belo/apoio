package com.eventos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "locais")
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco;
    private String cidade;

    public Local() {}

    public Local(String nome, String endereco, String cidade) {
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getCidade() { return cidade; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public void setCidade(String cidade) { this.cidade = cidade; }
}
