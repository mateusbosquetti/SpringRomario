package net.weg.gestaoeventos.Service;

import lombok.AllArgsConstructor;
import net.weg.gestaoeventos.Controller.dto.InscricaoRequestDTO;
import net.weg.gestaoeventos.Entity.Evento;
import net.weg.gestaoeventos.Entity.Inscricao;
import net.weg.gestaoeventos.Repository.EventoRepository;
import net.weg.gestaoeventos.Repository.InscricaoRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class InscricaoService {

    private InscricaoRepository repository;
    private EventoRepository eventoRepository;

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
        Evento evento = eventoRepository.findById(id).orElseThrow(NoSuchElementException::new);
        inscricao.setEvento(evento);
        return repository.save(inscricao);
    }

    public Inscricao buscarUmaInscricao(Integer id){
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<Inscricao> buscarInscricoes() {
        return repository.findAll();
    }

    public void removerInscricao(Integer id){
        repository.delete(buscarUmaInscricao(id));
    }

}
