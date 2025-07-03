package com.exemplo.app.tests;

import com.exemplo.app.controllers.PedidoController;
import com.exemplo.app.models.Pedido;
import com.exemplo.app.services.PedidoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Test
    public void deveListarPedidos() throws Exception {
        when(pedidoService.listar()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/pedidos"))
                .andExpect(status().isOk());
    }
}
