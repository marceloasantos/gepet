package br.com.fatec.pet.gepet.business.service;

import br.com.fatec.pet.gepet.database.repository.VacinaRepository;
import br.com.fatec.pet.gepet.domain.model.Vacina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("vacinaService")
public class VacinaServiceImpl implements VacinaService {
    @Autowired
    private VacinaRepository vacinaRepository;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void removerVacina(UUID id) {
        vacinaRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Vacina> listarVacinas() {
        return vacinaRepository.findAll();
    }
}
