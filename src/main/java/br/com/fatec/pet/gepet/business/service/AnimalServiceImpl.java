package br.com.fatec.pet.gepet.business.service;

import br.com.fatec.pet.gepet.database.repository.AnimalRepository;
import br.com.fatec.pet.gepet.database.repository.UsuarioRepository;
import br.com.fatec.pet.gepet.domain.model.Animal;
import br.com.fatec.pet.gepet.domain.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AnimalServiceImpl implements AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Animal cadastrarPet(String nome, Double peso, UUID idDono) {
        Usuario usuario = usuarioRepository.getOne(idDono);

        Animal animal = new Animal(nome, peso, usuario);
        animalRepository.save(animal);

        return animal;
    }

    @Override
    public void removerPet(UUID id) {
        animalRepository.deleteById(id);
    }
}
