package io.github.geancarloslc.api.services.impl;

import io.github.geancarloslc.api.domain.Usuario;
import io.github.geancarloslc.api.domain.dto.UsuarioDTO;
import io.github.geancarloslc.api.repositories.UsuarioRepository;
import io.github.geancarloslc.api.services.UsuarioService;
import io.github.geancarloslc.api.services.exceptions.DataIntegratyViolationExecption;
import io.github.geancarloslc.api.services.exceptions.ObjectNotFoundExecption;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Usuario findById(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new ObjectNotFoundExecption("Cliente não encontrado."));
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario create(UsuarioDTO usuarioDTO) {
        findByEmail(usuarioDTO);
        return usuarioRepository.save(modelMapper.map(usuarioDTO, Usuario.class));
    }

    private void findByEmail(UsuarioDTO usuarioDTO){
        Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioDTO.getEmail());
        if (usuario.isPresent()){
            throw new DataIntegratyViolationExecption("E-mail já cadastrado no sistema");
        }
    }
}
