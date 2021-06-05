package br.com.fatec.pet.gepet.business.service;

import br.com.fatec.pet.gepet.database.repository.AutorizacaoRepository;
import br.com.fatec.pet.gepet.database.repository.UsuarioRepository;
import br.com.fatec.pet.gepet.domain.model.Autorizacao;
import br.com.fatec.pet.gepet.domain.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AutorizacaoRepository autorizacaoRepository;

    @Autowired
    private PasswordEncoder passEncoder;

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Usuario criarUsuario(String nome, String email, String senha, String nomeAutorizacao) {
        Set<Autorizacao> autorizacoes = new HashSet<Autorizacao>();
        Autorizacao autorizacao = autorizacaoRepository.findByNome(nomeAutorizacao);

        if (null == autorizacao) {
            autorizacao = new Autorizacao();
            autorizacao.setNome(nomeAutorizacao);
            autorizacaoRepository.save(autorizacao);
            autorizacoes.add(autorizacao);
        } else {
            autorizacoes.add(autorizacao);
        }

        Usuario usuario = new Usuario(nome, email, passEncoder.encode(senha), autorizacoes);
        usuarioRepository.save(usuario);

        return usuario;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorNome(String nome) {
        return usuarioRepository.findTop1ByNome(nome);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Usuario editarUsuario(String nome, String email, String nomeAutorizacao) {
        Usuario usuario = usuarioRepository.findTop1ByNomeOrEmail(nome, email);

        Set<Autorizacao> autorizacoes = new HashSet<Autorizacao>();
        Autorizacao autorizacao = autorizacaoRepository.findByNome(nomeAutorizacao);

        if (null == autorizacao) {
            autorizacao = new Autorizacao();
            autorizacao.setNome(nomeAutorizacao);
            autorizacaoRepository.save(autorizacao);
            autorizacoes.add(autorizacao);
        } else {
            autorizacoes.add(autorizacao);
        }

        if (usuario != null) {
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setAutorizacoes(autorizacoes);
        }

        usuarioRepository.save(usuario);

        return usuario;
    }

    @Override
    public void removerUsuario(UUID id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario buscarUsuarioPorId(UUID id) {
        return usuarioRepository.getOne(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findTop1ByNomeOrEmail(username, username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário " + username + " não encontrado");
        }
        return User.builder().username(username)
                .password(usuario.getSenha())
                .authorities(usuario.getAutorizacoes().stream().map(Autorizacao::getNome)
                        .collect(Collectors.toList()).toArray(new String[usuario.getAutorizacoes().size()]))
                .build();
    }
}
