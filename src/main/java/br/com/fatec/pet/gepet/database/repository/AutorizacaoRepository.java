package br.com.fatec.pet.gepet.database.repository;

import br.com.fatec.pet.gepet.domain.model.Autorizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AutorizacaoRepository extends JpaRepository<Autorizacao, UUID> {
    public Autorizacao findByNome(String nome);
}
