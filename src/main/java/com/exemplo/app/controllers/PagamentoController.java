package com.exemplo.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.exemplo.app.models.Pagamento;
import com.exemplo.app.services.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<Pagamento> pagar(@RequestBody Pagamento pagamento) {
        return ResponseEntity.ok(pagamentoService.pagar(pagamento));
    }
}
