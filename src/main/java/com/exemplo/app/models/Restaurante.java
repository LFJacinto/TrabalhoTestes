package com.exemplo.app.models;

public class Restaurante {
    private Long id;
    private String nome;
    private boolean aberto;

    public Restaurante(Long id, String nome, boolean aberto) {
        this.id = id;
        this.nome = nome;
        this.aberto = aberto;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public boolean isAberto() { return aberto; }
    public void setAberto(boolean aberto) { this.aberto = aberto; }
}
