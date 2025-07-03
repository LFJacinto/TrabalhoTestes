package com.exemplo.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exemplo.app.models.Pagamento;
import com.exemplo.app.repositories.PagamentoRepository;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    public Pagamento pagar(Pagamento pagamento) {
        pagamento.setStatus("PAGO");
        return pagamentoRepository.save(pagamento);
    }
}
