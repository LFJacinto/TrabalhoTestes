package com.exemplo.app.models;

import java.time.Instant;

public class CarrinhoItem {
    private Produto produto;
    private int quantidade;
    private Instant adicionadoEm;

    public CarrinhoItem(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.adicionadoEm = Instant.now();
    }

    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public Instant getAdicionadoEm() { return adicionadoEm; }

    public double getSubtotal() { return quantidade * produto.getPreco(); }
}
