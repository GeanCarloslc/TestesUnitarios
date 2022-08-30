package io.github.geancarloslc.api.resources;

import io.github.geancarloslc.api.domain.Usuario;
import io.github.geancarloslc.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/{id}")
    public Usuario buscarCliente(@PathVariable Integer id) {
        return usuarioService
                .findById(id);
    }
}
