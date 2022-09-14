package io.github.geancarloslc.api.resources;

import io.github.geancarloslc.api.domain.Usuario;
import io.github.geancarloslc.api.domain.dto.UsuarioDTO;
import io.github.geancarloslc.api.repositories.UsuarioRepository;
import io.github.geancarloslc.api.services.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioResourceTest {

    @InjectMocks
    private UsuarioResource resource;

    @Mock //Dados locais
    private UsuarioServiceImpl service;

    @Mock
    private ModelMapper modelMapper;

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;

    public static final Integer ID = 1;
    public static final String NOME = "Gean";
    public static final String EMAIL = "gean@gmail.com";
    public static final String PASSWORD = "123";
    public static final int INDEX = 0;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsuario();
    }

    @Test
    void buscarCliente() {
    }

    @Test
    void buscarTodosCliente() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUsuario() {
        usuario = new Usuario(ID, NOME, EMAIL, PASSWORD);
        usuarioDTO = new UsuarioDTO(ID, NOME, EMAIL, PASSWORD);
    }
}