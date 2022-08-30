package io.github.geancarloslc.api.config;

import io.github.geancarloslc.api.domain.Usuario;
import io.github.geancarloslc.api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Bean
    public void startDB(){
        Usuario usuario1 = new Usuario(null, "Gean", "gean@email.com", "123");
        Usuario usuario2 = new Usuario(null, "Leandro", "leandro@email.com", "123");

        usuarioRepository.saveAll(List.of(usuario1, usuario2));

    }
}
