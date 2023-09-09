package br.com.fiap.studitproject.services;

import br.com.fiap.studitproject.entities.Credencial;
import br.com.fiap.studitproject.entities.Token;
import br.com.fiap.studitproject.entities.Usuario;
import br.com.fiap.studitproject.repositories.UsuarioRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Value("${jwt.secret}")
    String secret;

    public Token generateToken(Credencial credencial) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var token = JWT.create()
                .withSubject(credencial.email())
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                .withIssuer("studitproject")
                .sign(alg);

        return new Token(token, "JWT", "Bearer");
    }

    public Usuario valideAndGetUserBy(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var email =  JWT.require(alg)
                .withIssuer("studiproject")
                .build()
                .verify(token)
                .getSubject()
                ;

        return usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
