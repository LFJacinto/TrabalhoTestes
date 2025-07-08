package com.exemplo.app.models;

import jakarta.persistence.*;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double preco;
    @ManyToOne
    private Restaurante restaurante;

    public Produto() {}

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
