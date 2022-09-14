package io.github.geancarloslc.api.services.impl;

import io.github.geancarloslc.api.domain.Usuario;
import io.github.geancarloslc.api.domain.dto.UsuarioDTO;
import io.github.geancarloslc.api.repositories.UsuarioRepository;

import io.github.geancarloslc.api.services.exceptions.DataIntegratyViolationExecption;
import io.github.geancarloslc.api.services.exceptions.ObjectNotFoundExecption;
import javassist.tools.rmi.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    public static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado.";
    public static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema.";
    public static final int INDEX = 0;

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
    void whenFindByIdThenReturnAnObjectNotFoundExeception(){
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundExecption(CLIENTE_NAO_ENCONTRADO));

        try {
            service.findById(ID);
        } catch (Exception ex){
            assertEquals(ObjectNotFoundExecption.class, ex.getClass());
            assertEquals(CLIENTE_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void whenFindAllReturnAnListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> response = service.findAll();
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Usuario.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NOME, response.get(INDEX).getNome());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(usuario);

        Usuario response = service.create(usuarioDTO);

        assertNotNull(response);
        assertEquals(Usuario.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenCreateThenReturnAnDataIntegreityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUsuario);

        try{
            optionalUsuario.get().setId(2);
            service.create(usuarioDTO);
        } catch (Exception ex){
            assertEquals(DataIntegratyViolationExecption.class, ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(usuario);

        Usuario response = service.update(usuarioDTO);

        assertNotNull(response);
        assertEquals(Usuario.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenUpdateThenReturnAnDataIntegreityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUsuario);

        try{
            optionalUsuario.get().setId(2);
            service.update(usuarioDTO);
        } catch (Exception ex){
            assertEquals(DataIntegratyViolationExecption.class, ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, ex.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        when(repository.findById(anyInt())).thenReturn(optionalUsuario);
        doNothing().when(repository).deleteById(anyInt());
        service.delete(ID);
        verify(repository, times(1)).deleteById(anyInt());
    }

    private void startUsuario() {
        usuario = new Usuario(ID, NOME, EMAIL, PASSWORD);
        usuarioDTO = new UsuarioDTO(ID, NOME, EMAIL, PASSWORD);
        optionalUsuario = Optional.of(new Usuario(ID, NOME, EMAIL, PASSWORD));
    }
}