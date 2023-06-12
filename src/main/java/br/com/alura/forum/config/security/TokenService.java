package br.com.alura.forum.config.security;

import br.com.alura.forum.modelo.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;
    public String create(Authentication auth) {
        Usuario user = (Usuario) auth.getPrincipal();
        Date date = new Date();
        System.out.println("TESTE"+ expiration +"->"+Long.parseLong(expiration));
        Date expirationDate = new Date(date.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API Forum Alura")
                .setSubject(user.getId().toString())
                .setIssuedAt(date)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.ES256, this.secret)
                .compact();
    }
}
