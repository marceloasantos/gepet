package br.com.fatec.pet.gepet.business.service;

import br.com.fatec.pet.gepet.domain.model.Animal;
import br.com.fatec.pet.gepet.domain.model.Vacina;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface AnimalService {
    public Animal cadastrarPet(String nome, Double peso, UUID idDono);
    public Animal cadastrarPetComVacinas(String nome, Double peso, UUID idDono, Set<Vacina> vacinas);
    public Animal editarPet(UUID id, String nome, Double peso);
    public void removerPet(UUID id);
    List<Animal> findByDonoId();
}
