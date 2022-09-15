package io.github.geancarloslc.api.resources;

import io.github.geancarloslc.api.domain.Usuario;
import io.github.geancarloslc.api.domain.dto.UsuarioDTO;
import io.github.geancarloslc.api.services.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void whenBuscarClienteThenReturnSuccess() {
        when(service.findById(anyInt())).thenReturn(usuario);
        when(modelMapper.map(any(), any())).thenReturn(usuarioDTO);

        ResponseEntity<UsuarioDTO> response = resource.buscarCliente(ID);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UsuarioDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getNome());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());
    }

    @Test
    void whenBuscarTodosClienteThenReturnAListOfUserDTO() {
        when(service.findAll()).thenReturn(List.of(usuario));
        when(modelMapper.map(any(), any())).thenReturn(usuarioDTO);

        ResponseEntity<List<UsuarioDTO>> response = resource.buscarTodosCliente();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UsuarioDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NOME, response.getBody().get(INDEX).getNome());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());
    }

    @Test
    void whenCreateThenReturnCreated() {
        when(service.create(any())).thenReturn(usuario);
        ResponseEntity<UsuarioDTO> response = resource.create(usuarioDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(service.update(usuarioDTO)).thenReturn(usuario);
        when(modelMapper.map(any(), any())).thenReturn(usuarioDTO);

        ResponseEntity<UsuarioDTO> response = resource.update(ID, usuarioDTO);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UsuarioDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getNome());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());
    }

    @Test
    void delete() {
    }

    private void startUsuario() {
        usuario = new Usuario(ID, NOME, EMAIL, PASSWORD);
        usuarioDTO = new UsuarioDTO(ID, NOME, EMAIL, PASSWORD);
    }
}