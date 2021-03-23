package br.com.fatec.pet.gepet.business.service;

import br.com.fatec.pet.gepet.domain.model.Usuario;

public interface UsuarioService {
    public Usuario criarUsuario(String nome, String email, String senha, String nomeAutorizacao);
}
