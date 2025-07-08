package com.exemplo.app.models;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class CarrinhoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
import java.time.Instant;

public class CarrinhoItem {
    private Produto produto;
    private int quantidade;
    private Instant adicionadoEm;

    public CarrinhoItem() {}
  
    public CarrinhoItem(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.adicionadoEm = Instant.now();
    }

    public Long getId() { return id; }

    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public Instant getAdicionadoEm() { return adicionadoEm; }

    public double getSubtotal() { return quantidade * produto.getPreco(); }
}
