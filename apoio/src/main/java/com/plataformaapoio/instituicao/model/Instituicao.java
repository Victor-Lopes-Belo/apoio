package com.plataformaapoio.instituicao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "instituicoes")
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String endereco;
    private String contato;
    private String responsavel;

    public Instituicao() {
        
    }

    public Instituicao(String nome, String endereco, String contato, String responsavel) {
        this.nome = nome;
        this.endereco = endereco;
        this.contato = contato;
        this.responsavel = responsavel;
    }

    public Integer getId() { 
        return id; 
    }

    public void setId(Integer id) { 
        this.id = id; 
    }

    public String getNome() { 
        return nome; 
    }

    public void setNome(String nome) { 
        this.nome = nome; 
    }

    public String getEndereco() { 
        return endereco; 
    }

    public void setEndereco(String endereco) { 
        this.endereco = endereco; 
    }

    public String getContato() { 
        return contato; 
    }

    public void setContato(String contato) { 
        this.contato = contato; 
    }

    public String getResponsavel() { 
        return responsavel; 
    }

    public void setResponsavel(String responsavel) { 
        this.responsavel = responsavel; 
    }
}
