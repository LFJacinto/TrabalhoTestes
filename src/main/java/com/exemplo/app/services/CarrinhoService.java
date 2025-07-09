package com.exemplo.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exemplo.app.repositories.CarrinhoRepository;

@Service
public class CarrinhoService {
    public static final int LIMITE_MAXIMO_ITENS = 100;

    private final CarrinhoRepository carrinhoRepository;

    @Autowired
    public CarrinhoService(CarrinhoRepository carrinhoRepository) {
        this.carrinhoRepository = carrinhoRepository;
    }

    public String adicionarItem(String produto, int quantidade) {
        int totalAtual = carrinhoRepository.quantidadeTotalItens();
        if (totalAtual + quantidade > LIMITE_MAXIMO_ITENS) {
            return "Ops! Você atingiu o limite máximo de itens no carrinho";
        }
        carrinhoRepository.adicionar(produto, quantidade);
        return "Item adicionado";
    }
}
