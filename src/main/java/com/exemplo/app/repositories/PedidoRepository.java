package com.exemplo.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exemplo.app.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
