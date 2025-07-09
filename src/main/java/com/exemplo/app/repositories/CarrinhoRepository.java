package com.exemplo.app.repositories;

public interface CarrinhoRepository {
    int quantidadeTotalItens();
    void adicionar(String produto, int quantidade);
}
