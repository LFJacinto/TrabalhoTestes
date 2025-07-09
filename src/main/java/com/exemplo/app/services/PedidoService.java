package com.exemplo.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exemplo.app.models.Pedido;
import com.exemplo.app.repositories.PedidoRepository;
import java.time.LocalDateTime;


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

    public boolean horarioDisponivel(Pedido pedido) {
        List<Pedido> pedidosAgendados = pedidoRepository.findByRestauranteAndDataHoraEntrega(
                pedido.getRestaurante(), pedido.getDataHoraEntrega());

        return pedidosAgendados.isEmpty();
    }

    public Pedido agendar(Pedido pedido) {
        pedido.setAgendado(true);
        return pedidoRepository.save(pedido);
    }
}
