package br.com.fatec.pet.gepet;

import br.com.fatec.pet.gepet.business.service.AnimalServiceImpl;
import br.com.fatec.pet.gepet.business.service.UsuarioServiceImpl;
import br.com.fatec.pet.gepet.business.service.VacinaServiceImpl;
import br.com.fatec.pet.gepet.database.repository.AnimalRepository;
import br.com.fatec.pet.gepet.database.repository.UsuarioRepository;
import br.com.fatec.pet.gepet.database.repository.VacinaRepository;
import br.com.fatec.pet.gepet.domain.model.Animal;
import br.com.fatec.pet.gepet.domain.model.Vacina;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@WithMockUser(username = "marcelo", password = "12345", roles = "ADMIN")
@SpringBootTest
@Transactional
@Rollback
class GepetApplicationTests {
	@Autowired private AnimalServiceImpl _animalService;
	@Autowired private UsuarioServiceImpl _usuarioService;
	@Autowired private VacinaServiceImpl _vacinaService;

	@Autowired private AnimalRepository _animalRepository;
	@Autowired private UsuarioRepository _usuarioRepository;
	@Autowired private VacinaRepository _vacinaRepository;

	// Pets
	@Test
	void deveCadastrarPet() {
		UUID idDono = UUID.randomUUID();
		Animal animalCadastrado = _animalService.cadastrarPet("Nala", 7.5, idDono);

		Assert.notNull(animalCadastrado);
	}

	@Test
	void deveCadastrarPetComVacina() {
		UUID idDono = UUID.randomUUID();
		Set<Vacina> vacinas = new HashSet<Vacina>();

		vacinas.add(new Vacina(UUID.randomUUID(), "Anti Rabica"));

		Animal animalCadastrado = _animalService.cadastrarPetComVacinas("Nala", 7.5, idDono, vacinas);

		Assert.notNull(animalCadastrado);
	}

	@Test
	void deveEditarPet() {
		UUID idDono = UUID.randomUUID();
		Set<Vacina> vacinas = new HashSet<Vacina>();

		vacinas.add(new Vacina(UUID.randomUUID(), "Anti Rabica"));

		Animal animalCadastrado = _animalService.cadastrarPetComVacinas("Nala", 7.5, idDono, vacinas);
		Animal animalEditado = _animalService.editarPet(animalCadastrado.getId(), animalCadastrado.getNome(), 8.0);

		Assert.notNull(animalEditado);
	}

	@Test
	void deveExcluirPet() {
		UUID idDono = UUID.randomUUID();
		Set<Vacina> vacinas = new HashSet<Vacina>();

		vacinas.add(new Vacina(UUID.randomUUID(), "Anti Rabica"));

		Animal animalCadastrado = _animalService.cadastrarPetComVacinas("Nala", 7.5, idDono, vacinas);
		_animalService.removerPet(animalCadastrado.getId());
		Optional<Animal> animalNaoEncontrado = _animalRepository.findById(animalCadastrado.getId());

		Assert.isTrue(!animalNaoEncontrado.isPresent());
	}

	// Vacinas
	@Test
	void deveCadastrarVacina() {
		Vacina vacinaCadastrada = _vacinaRepository.save(new Vacina(UUID.randomUUID(), "Antirabica"));

		Assert.notNull(vacinaCadastrada);
	}
}
