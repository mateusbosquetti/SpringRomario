package net.weg.gestaoeventos.Service;

import lombok.AllArgsConstructor;
import net.weg.gestaoeventos.Controller.dto.InscricaoRequestDTO;
import net.weg.gestaoeventos.Entity.Inscricao;
import net.weg.gestaoeventos.Repository.InscricaoRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InscricaoService {

    private InscricaoRepository repository;

    public Inscricao cadastro(InscricaoRequestDTO dto) {
        Inscricao inscricao = dto.conversao();
        return repository.save(inscricao);
    }
}
