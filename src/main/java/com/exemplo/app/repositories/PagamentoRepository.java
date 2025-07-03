package com.exemplo.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exemplo.app.models.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
