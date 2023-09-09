package br.com.fiap.studitproject.controllers;

import br.com.fiap.studitproject.entities.Credencial;
import br.com.fiap.studitproject.entities.Usuario;
import br.com.fiap.studitproject.repositories.UsuarioRepository;
import br.com.fiap.studitproject.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UsuarioController {
    @Autowired
    UsuarioRepository repository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenService tokenService;

    @PostMapping("/api/registrar")
    public ResponseEntity<Usuario> registrar(@RequestBody @Valid Usuario usuario) {
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        repository.save(usuario);

        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestBody @Valid Credencial credencial) {
        manager.authenticate(credencial.toAuthentication());
        var token = tokenService.generateToken(credencial);
        Optional<Usuario> usuario = repository.findByEmail(credencial.email());
        if (usuario.isPresent()) {
            return ResponseEntity.ok().header("Authorization", token.toString()).body(usuario.get());
        }
        return ResponseEntity.badRequest().build();
    }
}
