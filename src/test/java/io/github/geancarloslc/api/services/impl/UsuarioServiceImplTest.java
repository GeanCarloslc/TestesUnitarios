package io.github.geancarloslc.api.services.impl;

import io.github.geancarloslc.api.domain.Usuario;
import io.github.geancarloslc.api.domain.dto.UsuarioDTO;
import io.github.geancarloslc.api.repositories.UsuarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UsuarioServiceImplTest {


    @InjectMocks //Instancia real com dados do banco de dados
    private UsuarioServiceImpl service;

    @Mock //Dados locais
    private UsuarioRepository repository;

    @Mock
    private ModelMapper modelMapper;

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;
    private Optional<Usuario> optionalUsuario;
    public static final Integer ID = 1;
    public static final String NOME = "Gean";
    public static final String EMAIL = "gean@gmail.com";
    public static final String PASSWORD = "123";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsuario();
    }

    @Test
    void findById() {
        when(repository.findById(anyInt())).thenReturn(optionalUsuario);

        Usuario response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Usuario.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void findAll() {
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
        optionalUsuario = Optional.of(new Usuario(ID, NOME, EMAIL, PASSWORD));
    }
}