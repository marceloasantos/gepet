package br.com.fatec.pet.gepet.business.service;

import br.com.fatec.pet.gepet.domain.model.Animal;

import java.util.UUID;

public interface AnimalService {
    public Animal cadastrarPet(String nome, Double peso, UUID idDono);
    public void removerPet(UUID id);
}
