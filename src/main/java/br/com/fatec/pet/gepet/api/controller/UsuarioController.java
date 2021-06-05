package br.com.fatec.pet.gepet.api.controller;

import br.com.fatec.pet.gepet.api.dto.UsuarioDTO;
import br.com.fatec.pet.gepet.api.view.View;
import br.com.fatec.pet.gepet.business.service.UsuarioService;
import br.com.fatec.pet.gepet.domain.model.Usuario;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping(value = "")
    @JsonView(View.UsuarioCompleto.class)
    public Usuario buscarUsuarioPorNome(@RequestParam String nome) {
        return service.buscarUsuarioPorNome(nome);
    }

    @GetMapping(value = "/buscar-por-id")
    @JsonView(View.UsuarioCompleto.class)
    public Usuario buscarUsuarioPorId(@RequestParam UUID id) {
        return service.buscarUsuarioPorId(id);
    }

    @GetMapping(value = "/todos")
    @JsonView(View.UsuarioResumo.class)
    public List<Usuario> listarUsuarios() {
        return service.listarUsuarios();
    }

    @PutMapping(value = "/editar")
    public Usuario editarUSuario(@RequestBody UsuarioDTO usuario) {
        return service.editarUsuario(usuario.getNome(), usuario.getEmail(), usuario.getAutorizacao());
    }

    @DeleteMapping(value = "/excluir")
    public void excluirPet(@RequestParam UUID id) {
        service.removerUsuario(id);
    }
}
