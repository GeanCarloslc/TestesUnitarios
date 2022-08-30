package io.github.geancarloslc.api.services;

import io.github.geancarloslc.api.domain.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Usuario findById(Integer id);
}
