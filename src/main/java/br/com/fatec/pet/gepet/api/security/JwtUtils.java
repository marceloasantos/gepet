package br.com.fatec.pet.gepet.api.security;

import br.com.fatec.pet.gepet.api.dto.UsuarioDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.json.JsonParseException;
import org.springframework.security.core.userdetails.User;
import io.jsonwebtoken.Jwts;

import java.io.IOException;
import java.util.Date;

public class JwtUtils {
    private static final String KEY = "spring.jwt.sec";

    public static String generateToken(User usuario) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        UsuarioDTO usuarioSemSenha = new UsuarioDTO();
        usuarioSemSenha.setNome(usuario.getUsername());

        if (!usuario.getAuthorities().isEmpty()) {
            usuarioSemSenha.setAutorizacao(usuario.getAuthorities().iterator().next().getAuthority());
        }

        String usuarioJson = mapper.writeValueAsString(usuarioSemSenha);
        Date agora = new Date();
        Long hora = 1000L * 60L * 60L;

        return Jwts.builder().claim("userDetails", usuarioJson)
                .setIssuer("br.com.fatec.pet.gepet")
                .setSubject(usuario.getUsername())
                .setExpiration(new Date(agora.getTime() + hora)).signWith(SignatureAlgorithm.HS512, KEY).compact();
    }

    public static User parseToken(String token) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        String credentialsJson = Jwts.parser().setSigningKey(KEY)
                .parseClaimsJws(token).getBody().get("userDetails", String.class);

        UsuarioDTO usuario = mapper.readValue(credentialsJson, UsuarioDTO.class);

        return (User) User.builder()
                .username(usuario.getNome())
                .password("secret")
                .authorities(usuario.getAutorizacao())
                .build();
    }
}
