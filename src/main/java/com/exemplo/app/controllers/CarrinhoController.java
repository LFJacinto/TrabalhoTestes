package com.exemplo.app.controllers;

import com.exemplo.app.models.CarrinhoItem;
import com.exemplo.app.services.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping("/adicionar")
    public ResponseEntity<?> adicionar(@RequestBody Map<String, String> body) {
        long produtoId = Long.parseLong(body.get("produtoId"));
        int quantidade = Integer.parseInt(body.get("quantidade"));
        try {
            CarrinhoItem item = carrinhoService.adicionarItem(produtoId, quantidade);
            return ResponseEntity.ok(item);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<CarrinhoItem> listar() {
        return carrinhoService.listarItens();
    }

    @GetMapping("/total")
    public Map<String, Double> total() {
        return Map.of("total", carrinhoService.calcularTotal());
    }
}
