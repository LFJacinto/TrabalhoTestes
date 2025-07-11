package com.exemplo.app.controllers;

import com.exemplo.app.models.Usuario;
import com.exemplo.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario usuario) {
        try {
            Usuario criado = usuarioService.cadastrar(usuario);
            return ResponseEntity.status(201).body(criado);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestParam String email, @RequestParam String senha) {
        Optional<Usuario> usuario = usuarioService.login(email, senha);
        return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id, @RequestBody(required = false) Map<String, String> body) {
        String detalhes = body != null ? body.get("detalhes") : null;
        try {
            String mensagem = usuarioService.excluir(id, detalhes);
            return ResponseEntity.ok(mensagem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/alterar-senha")
    public ResponseEntity<?> alterarSenha(@RequestBody Map<String, String> body) {
        String senhaAtual = body.get("senhaAtual");
        String novaSenha = body.get("novaSenha");
        String confirmacaoNovaSenha = body.get("confirmacaoNovaSenha");

        if (novaSenha.length() < 8) {
            return ResponseEntity.badRequest().body(Map.of("mensagem", "A senha deve ter pelo menos 8 caracteres."));
        }
        if (!novaSenha.matches(".*[A-Z].*")) {
            return ResponseEntity.badRequest().body(Map.of("mensagem", "A senha deve conter pelo menos uma letra maiúscula."));
        }
        if (!novaSenha.matches(".*[^a-zA-Z0-9].*")) {
            return ResponseEntity.badRequest().body(Map.of("mensagem", "A senha deve conter pelo menos um caractere especial."));
        }

        return ResponseEntity.ok(Map.of("mensagem", "Senha alterada com sucesso."));
    }
}
