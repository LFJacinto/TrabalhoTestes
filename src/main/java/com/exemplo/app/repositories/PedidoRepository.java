package com.exemplo.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exemplo.app.models.Pedido;
import com.exemplo.app.models.Pedido;
import com.exemplo.app.models.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByRestauranteAndDataHoraEntrega(Restaurante restaurante, LocalDateTime dataHoraEntrega);

}
