package io.github.geancarloslc.api.services.impl;

import io.github.geancarloslc.api.domain.Usuario;
import io.github.geancarloslc.api.repositories.UsuarioRepository;
import io.github.geancarloslc.api.services.UsuarioService;
import io.github.geancarloslc.api.services.exceptions.ObjectNotFoundExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario findById(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new ObjectNotFoundExecption("Cliente não encontrado."));
    }
}
