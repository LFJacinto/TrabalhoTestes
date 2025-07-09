package com.exemplo.app.tests;

import com.exemplo.app.repositories.CarrinhoRepository;
import com.exemplo.app.services.CarrinhoService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CarrinhoServiceTest {
    @Test
    void testAdicionarItensExcedendoLimite() {
        CarrinhoRepository repo = mock(CarrinhoRepository.class);
        CarrinhoService service = new CarrinhoService(repo);

        when(repo.quantidadeTotalItens()).thenReturn(0);

        String resultado = service.adicionarItem("Pastel", 1000);

        verify(repo, never()).adicionar(anyString(), anyInt());
        assertEquals(
            "Ops! Você atingiu o limite máximo de itens no carrinho",
            resultado
        );
    }
}
