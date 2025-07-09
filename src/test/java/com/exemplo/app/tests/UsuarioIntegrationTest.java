package com.exemplo.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    private String jsonBody(String atual, String nova, String confirmacao) throws Exception {
        Map<String, String> body = new HashMap<>();
        body.put("senhaAtual", atual);
        body.put("novaSenha", nova);
        body.put("confirmacaoNovaSenha", confirmacao);
        return mapper.writeValueAsString(body);
    }

    @Test
    @DisplayName("CT21 - Senha com menos de 8 caracteres")
    void senhaCurta() throws Exception {
        mockMvc.perform(post("/usuarios/alterar-senha")  // ajuste o endpoint conforme necessário
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody("SenhaAntiga123!", "aB1@cDe", "aB1@cDe")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem").value("A senha deve ter pelo menos 8 caracteres."));
    }

    @Test
    @DisplayName("CT22 - Senha sem letra maiúscula")
    void senhaSemMaiuscula() throws Exception {
        mockMvc.perform(post("/usuarios/alterar-senha")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody("SenhaAntiga123!", "novasenha123@", "novasenha123@")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem").value("A senha deve conter pelo menos uma letra maiúscula."));
    }

    @Test
    @DisplayName("CT23 - Senha sem caractere especial")
    void senhaSemCaractereEspecial() throws Exception {
        mockMvc.perform(post("/usuarios/alterar-senha")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody("SenhaAntiga123!", "NovaSenha123", "NovaSenha123")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem").value("A senha deve conter pelo menos um caractere especial."));
    }

    @Test
    @DisplayName("CT24 - Alteração de senha com sucesso")
    void alterarSenhaComSucesso() throws Exception {
        mockMvc.perform(post("/usuarios/alterar-senha")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody("SenhaAntiga123!", "NovaSenha@2025", "NovaSenha@2025")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensagem").value("Senha alterada com sucesso."));
    }
}
