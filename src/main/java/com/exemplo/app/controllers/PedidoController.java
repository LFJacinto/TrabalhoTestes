package com.exemplo.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.exemplo.app.models.Pedido;
import com.exemplo.app.services.PedidoService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.criar(pedido));
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listar() {
        return ResponseEntity.ok(pedidoService.listar());
    }

    @PostMapping("/agendar")
    public ResponseEntity<?> agendarPedido(@RequestBody Pedido pedido) {
        if (pedido.getDataHoraEntrega() == null || pedido.getDataHoraEntrega().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body(Map.of("mensagem", "A data/hora de entrega deve ser futura."));
        }

        if (!pedido.getRestaurante().isAgendamentoAtivo()) {
            return ResponseEntity.badRequest().body(Map.of("mensagem", "Este restaurante não aceita agendamento de pedidos."));
        }

        boolean disponivel = pedidoService.horarioDisponivel(pedido);
        if (!disponivel) {
            return ResponseEntity.badRequest().body(Map.of("mensagem", "Este horário de agendamento acabou de se esgotar. Por favor, selecione outro."));
        }

        Pedido salvo = pedidoService.agendar(pedido);
        return ResponseEntity.status(201).body(Map.of(
                "mensagem", "Pedido agendado com sucesso.",
                "pedidoId", salvo.getId()
        ));
    }
}
