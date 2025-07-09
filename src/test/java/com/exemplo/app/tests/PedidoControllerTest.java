package com.exemplo.app.tests;

import com.exemplo.app.controllers.PedidoController;
import com.exemplo.app.models.Item;
import com.exemplo.app.models.Pedido;
import com.exemplo.app.models.Restaurante;
import com.exemplo.app.services.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
    import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;


@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());



    @Test
    public void deveListarPedidos() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setDescricao("Pedido de Teste");
        pedido.setValor(100.0);

        when(pedidoService.listar()).thenReturn(List.of(pedido));

        mockMvc.perform(get("/pedidos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].descricao").value("Pedido de Teste"))
                .andExpect(jsonPath("$[0].valor").value(100.0));
    }

    @Test
    @DisplayName("CT39 - Agendar um pedido para o dia seguinte em um restaurante que não permite agendamento")
    void agendarPedidoRestauranteNaoAceitaAgendamento() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setDataHoraEntrega(LocalDateTime.now().plusDays(1));
        pedido.setRestaurante(new Restaurante());
        pedido.setItens(List.of(new Item("Pão"), new Item("Queijo")));
        pedido.setFormaPagamento("Cartão");

        when(pedidoService.horarioDisponivel(any(Pedido.class))).thenReturn(true);
        when(pedidoService.agendar(any())).thenReturn(pedido);

        mockMvc.perform(post("/pedidos/agendar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(pedido)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.mensagem").value("Pedido não foi agendado."));
    }
}
