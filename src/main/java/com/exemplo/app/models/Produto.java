package com.exemplo.app.models;

public class Produto {
    private Long id;
    private String nome;
    private double preco;
    private Restaurante restaurante;

    public Produto(Long id, String nome, double preco, Restaurante restaurante) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.restaurante = restaurante;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public Restaurante getRestaurante() { return restaurante; }
}
