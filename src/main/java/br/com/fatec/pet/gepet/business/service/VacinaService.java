package br.com.fatec.pet.gepet.business.service;


import br.com.fatec.pet.gepet.domain.model.Vacina;

import java.util.List;
import java.util.UUID;

public interface VacinaService {
    void removerVacina(UUID id);
    List<Vacina> listarVacinas();
}
