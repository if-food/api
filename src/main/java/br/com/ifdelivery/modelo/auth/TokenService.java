package br.com.ifdelivery.modelo.auth;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import br.com.ifdelivery.modelo.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;


@Service
public class TokenService {

    @Value("${secret.value}")
    String secret;

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algo = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("fastash-api")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(gerarDataExpiracao()).sign(algo);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao criar o token");
        }

    }

    public String validarToken(String token) {


        try {

            Algorithm algo = Algorithm.HMAC256(secret);
            return JWT.require(algo)
                    .withIssuer("fastash-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            System.out.println(e.getMessage());
            return "";
        }

    }

    public Instant gerarDataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}