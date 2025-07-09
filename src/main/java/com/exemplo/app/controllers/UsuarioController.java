package com.exemplo.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.exemplo.app.models.Usuario;
import com.exemplo.app.services.UsuarioService;

import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

4ina2q-codex/implementar-testes-rt11-no-postman
    @PostMapping("/cadastro")

    @PostMapping
main
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
        String motivo = body != null ? body.get("motivos") : null;
        String detalhes = body != null ? body.get("detalhes") : null;
        try {
            String mensagem = usuarioService.excluir(id, motivo, detalhes);
            return ResponseEntity.ok(mensagem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
