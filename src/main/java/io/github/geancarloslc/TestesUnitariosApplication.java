package io.github.geancarloslc;

import io.github.geancarloslc.domain.Usuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TestesUnitariosApplication {

    public static void main(String[] args) {

        SpringApplication.run(TestesUnitariosApplication.class, args);

        Usuario usuario = null;


    }
}
