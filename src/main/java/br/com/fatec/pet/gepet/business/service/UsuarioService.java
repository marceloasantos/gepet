package br.com.fatec.pet.gepet.business.service;

import br.com.fatec.pet.gepet.domain.model.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UsuarioService extends UserDetailsService {
    Usuario criarUsuario(String nome, String email, String senha, String nomeAutorizacao);
    List<Usuario> listarUsuarios();
    Usuario buscarUsuarioPorNome(String nome);
    Usuario editarUsuario(String nome, String email, String nomeAutorizacao);
    void removerUsuario(UUID id);
    Usuario buscarUsuarioPorId(UUID id);
}
