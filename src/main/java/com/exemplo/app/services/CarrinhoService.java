package com.exemplo.app.services;

import com.exemplo.app.models.*;
import com.exemplo.app.repositories.CarrinhoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Service
public class CarrinhoService {
    private static final int LIMITE_ITENS = 50;
    private static final Duration TEMPO_MAX = Duration.ofMinutes(45);

    private final CarrinhoItemRepository carrinhoRepo;
    private final Map<Long, Restaurante> restaurantes = new HashMap<>();
    private final Map<Long, Produto> produtos = new HashMap<>();

    @Autowired
    public CarrinhoService(CarrinhoItemRepository carrinhoRepo) {
        this.carrinhoRepo = carrinhoRepo;
        Restaurante aberto = new Restaurante(1L, "Restaurante A", true);
        Restaurante fechado = new Restaurante(2L, "Restaurante B", false);
        restaurantes.put(1L, aberto);
        restaurantes.put(2L, fechado);

        produtos.put(1L, new Produto(1L, "Pastel", 10.0, aberto));
        produtos.put(2L, new Produto(2L, "Bolo", 5.0, aberto));
        produtos.put(3L, new Produto(3L, "Açaí", 12.0, fechado));
        produtos.put(4L, new Produto(4L, "Hambúrguer", 20.0, aberto));
        produtos.put(5L, new Produto(5L, "Pizza", 30.0, aberto));
    }

    public synchronized CarrinhoItem adicionarItem(long produtoId, int quantidade) {
        Produto produto = produtos.get(produtoId);
        if (produto == null) throw new IllegalArgumentException("Produto não encontrado");
        if (!produto.getRestaurante().isAberto()) {
            throw new IllegalStateException("Esta loja está fechada no momento.");
        }
        int totalQtd = carrinhoRepo.findAll().stream()
                .mapToInt(CarrinhoItem::getQuantidade).sum() + quantidade;
        if (totalQtd > LIMITE_ITENS) {
            throw new IllegalStateException("Ops! Você atingiu o limite máximo de itens no carrinho");
        }
        Optional<CarrinhoItem> existente = carrinhoRepo.findAll().stream()
                .filter(i -> i.getProduto().getId().equals(produtoId))
                .findFirst();
        if (existente.isPresent()) {
            CarrinhoItem item = existente.get();
            item.setQuantidade(item.getQuantidade() + quantidade);
            return carrinhoRepo.save(item);
        }
        CarrinhoItem novo = new CarrinhoItem(produto, quantidade);
        return carrinhoRepo.save(novo);
    }

    public synchronized List<CarrinhoItem> listarItens() {
        limparExpirados();
        return carrinhoRepo.findAll();
    }

    private void limparExpirados() {
        Instant agora = Instant.now();
        carrinhoRepo.findAll().stream()
            .filter(i -> Duration.between(i.getAdicionadoEm(), agora).compareTo(TEMPO_MAX) > 0)
            .forEach(i -> carrinhoRepo.deleteById(i.getId()));
    }

    public synchronized double calcularTotal() {
        limparExpirados();
        return carrinhoRepo.findAll().stream().mapToDouble(CarrinhoItem::getSubtotal).sum();
    }

    // util para testes
    public Map<Long, Produto> getProdutos() { return produtos; }
}
