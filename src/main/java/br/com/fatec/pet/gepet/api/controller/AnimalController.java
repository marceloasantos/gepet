package br.com.fatec.pet.gepet.api.controller;

import br.com.fatec.pet.gepet.api.dto.AnimalDTO;
import br.com.fatec.pet.gepet.api.dto.EditarAnimalDTO;
import br.com.fatec.pet.gepet.api.view.View;
import br.com.fatec.pet.gepet.business.service.AnimalService;
import br.com.fatec.pet.gepet.database.repository.AnimalRepository;
import br.com.fatec.pet.gepet.domain.model.Animal;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/animal")
@CrossOrigin
public class AnimalController {
    @Autowired
    private AnimalService service;

    @Autowired
    private AnimalRepository repository;

    @PostMapping(value = "/novo")
    @JsonView(View.PetResumo.class)
    public Animal cadastrarPet(@RequestBody AnimalDTO animal) {
        return service.cadastrarPet(animal.getNome(), animal.getPeso(), animal.getIdDono());
    }

    @GetMapping(value = "/todos")
    @JsonView(View.PetResumo.class)
    public List<Animal> listarAnimais() {
        return service.findByDonoId();
    }

    @GetMapping(value = "/buscar-por-id")
    @JsonView(View.PetCompleto.class)
    public Animal buscarPorId(@RequestParam UUID id) {
        return repository.getOne(id);
    }

    @DeleteMapping(value = "/excluir")
    public void excluirPet(@RequestParam UUID id) {
        service.removerPet(id);
    }

    @PutMapping(value = "/editar")
    @JsonView(View.PetResumo.class)
    public Animal editarPet(@RequestBody EditarAnimalDTO animal) {
        return service.editarPet(animal.getId(), animal.getNome(), animal.getPeso());
    }
}
