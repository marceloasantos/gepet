package br.com.fatec.pet.gepet.api.controller;

import br.com.fatec.pet.gepet.api.view.View;
import br.com.fatec.pet.gepet.business.service.VacinaService;
import br.com.fatec.pet.gepet.database.repository.VacinaRepository;
import br.com.fatec.pet.gepet.domain.model.Vacina;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/vacina")
@CrossOrigin
public class VacinaController {
    @Autowired
    private VacinaService service;

    @GetMapping(value = "/todos")
    @JsonView(View.VacinaResumo.class)
    public List<Vacina> listarVacinas() {
        List<Vacina> vacinas = service.listarVacinas();
        return vacinas;
    }

    @DeleteMapping(value = "/excluir")
    public void excluirVacina(@RequestParam UUID id) {
        service.removerVacina(id);
    }
}
