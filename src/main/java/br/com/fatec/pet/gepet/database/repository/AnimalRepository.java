package br.com.fatec.pet.gepet.database.repository;

import br.com.fatec.pet.gepet.domain.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, UUID> {
    public Set<Animal> findByDonoNomeOrDonoEmail(String nome, String email);
    public List<Animal> findByDonoId(UUID id);
}
