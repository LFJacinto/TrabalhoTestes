package com.exemplo.app.models;

import jakarta.persistence.*;

@Entity
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private boolean agendamentoAtivo;

    public Restaurante() {}

    // Construtor útil (opcional)
    public Restaurante(Long id, String nome, boolean agendamentoAtivo) {
        this.id = id;
        this.nome = nome;
        this.agendamentoAtivo = agendamentoAtivo;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public boolean isAgendamentoAtivo() { return agendamentoAtivo; }
    public void setAgendamentoAtivo(boolean agendamentoAtivo) { this.agendamentoAtivo = agendamentoAtivo; }

    public boolean isAberto() {
        return true;
    }
}
