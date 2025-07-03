package com.exemplo.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exemplo.app.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
