package br.com.fatec.pet.gepet.business.service;

import br.com.fatec.pet.gepet.domain.model.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {
    public Usuario criarUsuario(String nome, String email, String senha, String nomeAutorizacao);
}
