package com.exemplo.app.tests;

import com.exemplo.app.models.Usuario;
import com.exemplo.app.repositories.UsuarioRepository;
import com.exemplo.app.services.UsuarioService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {
    @Test
    public void deveLogarComCredenciaisValidas() {
        UsuarioRepository repo = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repo);

        Usuario u = new Usuario();
        u.setEmail("teste@teste.com");
        u.setSenha("123");
        when(repo.findByEmail("teste@teste.com")).thenReturn(u);

        Optional<Usuario> result = service.login("teste@teste.com", "123");
        assertTrue(result.isPresent());
    }

    @Test
    public void naoDeveLogarComSenhaInvalida() {
        UsuarioRepository repo = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repo);

        Usuario u = new Usuario();
        u.setEmail("teste@teste.com");
        u.setSenha("123");
        when(repo.findByEmail("teste@teste.com")).thenReturn(u);

        Optional<Usuario> result = service.login("teste@teste.com", "errado");
        assertTrue(result.isEmpty());
    }
}
