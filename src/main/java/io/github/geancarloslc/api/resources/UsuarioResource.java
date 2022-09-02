package io.github.geancarloslc.api.resources;

import io.github.geancarloslc.api.domain.Usuario;
import io.github.geancarloslc.api.domain.dto.UsuarioDTO;
import io.github.geancarloslc.api.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> buscarCliente(@PathVariable Integer id) {
        return ResponseEntity
                .ok()
                .body(modelMapper.map(usuarioService.findById(id), UsuarioDTO.class));
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioDTO>> buscarTodosCliente() {
        return ResponseEntity.ok()
                .body(usuarioService.findAll()
                        .stream().map(x -> modelMapper.map(x, UsuarioDTO.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioService.create(usuarioDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO){
        usuarioDTO.setId(id);
        Usuario usuario = usuarioService.update(usuarioDTO);
        return ResponseEntity
                .ok()
                .body(modelMapper.map(usuarioDTO, UsuarioDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioDTO> delete(@PathVariable Integer id){
        usuarioService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }


}
