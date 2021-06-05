package br.com.fatec.pet.gepet.api.controller;

import br.com.fatec.pet.gepet.api.dto.UsuarioDTO;
import br.com.fatec.pet.gepet.api.security.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private AuthenticationManager auth;

    @PostMapping(path = "/login")
    public UsuarioDTO login(@RequestBody UsuarioDTO login) throws JsonProcessingException {
        String username = login.getNome();

        if (username == null) {
            username = login.getEmail();
        }

        Authentication credentials = new UsernamePasswordAuthenticationToken(username, login.getSenha());
        User usuario = (User) auth.authenticate(credentials).getPrincipal();

        login.setSenha(null);
        login.setToken(JwtUtils.generateToken(usuario));
        login.setAutorizacao(usuario.getAuthorities().toArray()[0].toString());

        return login;
    }
}
