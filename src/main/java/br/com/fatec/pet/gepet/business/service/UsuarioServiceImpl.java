package br.com.fatec.pet.gepet.business.service;

import br.com.fatec.pet.gepet.database.repository.AutorizacaoRepository;
import br.com.fatec.pet.gepet.database.repository.UsuarioRepository;
import br.com.fatec.pet.gepet.domain.model.Autorizacao;
import br.com.fatec.pet.gepet.domain.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AutorizacaoRepository autorizacaoRepository;

    @Override
    @Transactional
    public Usuario criarUsuario(String nome, String email, String senha, String nomeAutorizacao) {
        Set<Autorizacao> autorizacoes = new HashSet<Autorizacao>();
        Autorizacao autorizacao = autorizacaoRepository.findByNome(nomeAutorizacao);

        if (null == autorizacao) {
            autorizacao = new Autorizacao();
            autorizacao.setNome(nomeAutorizacao);
            autorizacaoRepository.save(autorizacao);
            autorizacoes.add(autorizacao);
        }

        Usuario usuario = new Usuario(nome, email, senha, autorizacoes);
        usuarioRepository.save(usuario);

        return usuario;
    }
}
