package br.com.fatec.pet.gepet.api.controller;

import br.com.fatec.pet.gepet.api.dto.UsuarioDTO;
import br.com.fatec.pet.gepet.business.service.UsuarioService;
import br.com.fatec.pet.gepet.domain.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @PostMapping(value = "/novo")
    public Usuario cadastrarUsuario(@RequestBody UsuarioDTO usuario) {
        return service.criarUsuario(usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getAutorizacao());
    }
}
