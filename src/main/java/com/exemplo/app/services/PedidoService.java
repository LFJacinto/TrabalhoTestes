package com.exemplo.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exemplo.app.models.Pedido;
import com.exemplo.app.repositories.PedidoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido criar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> obter(Long id) {
        return pedidoRepository.findById(id);
    }
}
