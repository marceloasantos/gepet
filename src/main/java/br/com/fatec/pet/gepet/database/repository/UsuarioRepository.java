package br.com.fatec.pet.gepet.database.repository;

import br.com.fatec.pet.gepet.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    public Usuario findTop1ByNomeOrEmail(String nome, String email);
}
