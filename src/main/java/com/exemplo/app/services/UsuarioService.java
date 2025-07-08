package com.exemplo.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exemplo.app.models.Usuario;
import com.exemplo.app.repositories.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrar(Usuario usuario) {
        if (!"xxxxxx".equals(usuario.getCodigo())) {
            throw new IllegalArgumentException("Código informado inválido");
        }
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new IllegalStateException("Este email já está vinculado a uma conta");
        }
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> login(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return Optional.of(usuario);
        }
        return Optional.empty();
    }

    public String excluir(Long id, String detalhes) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
        return "Sua conta foi excluída com sucesso";
    }
}
