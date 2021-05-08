package br.com.fatec.pet.gepet.business.service;

import br.com.fatec.pet.gepet.database.repository.AnimalRepository;
import br.com.fatec.pet.gepet.database.repository.UsuarioRepository;
import br.com.fatec.pet.gepet.database.repository.VacinaRepository;
import br.com.fatec.pet.gepet.domain.model.Animal;
import br.com.fatec.pet.gepet.domain.model.Usuario;
import br.com.fatec.pet.gepet.domain.model.Vacina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class AnimalServiceImpl implements AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VacinaRepository vacinaRepository;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public Animal cadastrarPet(String nome, Double peso, UUID idDono) {
        Usuario usuario = usuarioRepository.getOne(idDono);

        Animal animal = new Animal(nome, peso, usuario);
        animalRepository.save(animal);

        return animal;
    }

    @Transactional
    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public Animal cadastrarPetComVacinas(String nome, Double peso, UUID idDono, Set<Vacina> vacinas) {
        if (!vacinas.isEmpty()) {
            vacinaRepository.saveAll(vacinas);
        }

        Animal animal = cadastrarPet(nome, peso, idDono);

        return animal;
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public Animal editarPet(UUID id, String nome, Double peso) {
        Animal pet = animalRepository.getOne(id);

        pet.setPeso(peso);
        pet.setNome(nome);
        animalRepository.save(pet);

        return pet;
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public void removerPet(UUID id) {
        animalRepository.deleteById(id);
    }
}
