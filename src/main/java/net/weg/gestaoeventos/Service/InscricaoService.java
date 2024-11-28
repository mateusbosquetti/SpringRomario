package net.weg.gestaoeventos.Service;

import lombok.AllArgsConstructor;
import net.weg.gestaoeventos.Controller.dto.InscricaoRequestDTO;
import net.weg.gestaoeventos.Entity.Evento;
import net.weg.gestaoeventos.Entity.Inscricao;
import net.weg.gestaoeventos.Repository.InscricaoRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class InscricaoService {

    private InscricaoRepository repository;

    public Inscricao cadastro(InscricaoRequestDTO dto) {
        Inscricao inscricao = dto.conversao();
        return repository.save(inscricao);
    }

    public Inscricao edicao(InscricaoRequestDTO dto, Integer id) {
        Inscricao inscricao = dto.conversao();
        inscricao.setId(id);
        return repository.save(inscricao);
    }

    public Inscricao trocarEvento(Integer id, Integer eventoId) {
        Inscricao inscricao = buscarUmaInscricao(id);

        inscricao.setEvento();
    }

    public Inscricao buscarUmaInscricao(Integer id){
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

}
