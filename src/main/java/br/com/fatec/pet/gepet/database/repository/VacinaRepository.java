package br.com.fatec.pet.gepet.database.repository;

import br.com.fatec.pet.gepet.domain.model.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@PreAuthorize("isAuthenticated()")
public interface VacinaRepository extends JpaRepository<Vacina, UUID> {
}
