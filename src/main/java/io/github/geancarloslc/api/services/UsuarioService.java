package io.github.geancarloslc.api.services;

import io.github.geancarloslc.api.domain.Usuario;
import io.github.geancarloslc.api.domain.dto.UsuarioDTO;

import java.util.List;


public interface UsuarioService {
    Usuario findById(Integer id);
    List<Usuario> findAll();
    Usuario create(UsuarioDTO usuarioDTO);
    Usuario update(UsuarioDTO usuarioDTO);
    void delete(Integer id);
}
