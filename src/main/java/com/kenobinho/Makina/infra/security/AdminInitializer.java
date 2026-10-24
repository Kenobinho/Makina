package com.kenobinho.Makina.infra.security;

import com.kenobinho.Makina.domain.usuarios.PerfilUsuario;
import com.kenobinho.Makina.domain.usuarios.Usuario;
import com.kenobinho.Makina.domain.usuarios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // se não existir um admin ele via criar automaticamente
        // essa parte é apenas para facilitar minha vida na apresentação
        if (usuarioRepository.findByLogin("admin") == null) {
            var senhaCriptografia = passwordEncoder.encode("admin123");
            var admin = new Usuario("admin", senhaCriptografia, PerfilUsuario.ADMIN);
            usuarioRepository.save(admin);
            System.out.println("Admin (automático) criado com sucesso!");
        }
    }
}
