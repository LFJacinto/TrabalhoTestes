package com.exemplo.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exemplo.app.models.CarrinhoItem;

public interface CarrinhoItemRepository extends JpaRepository<CarrinhoItem, Long> {}
