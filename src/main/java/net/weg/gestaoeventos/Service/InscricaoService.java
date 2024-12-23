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
    private EventoService eventoService;
    private ParticipanteService participanteService;

    public Inscricao adicionarInscricao(InscricaoRequestDTO dto) {
        Inscricao inscricao = convertToEntity(dto);
        return repository.save(inscricao);
    }

    public Inscricao editarInscricao(InscricaoRequestDTO dto, Integer id) {
        if (repository.existsById(id)) {
            Inscricao inscricao = convertToEntity(dto);
            inscricao.setId(id);
            return repository.save(inscricao);
        } else  {
            throw new NoSuchElementException();
        }
    }

    public Inscricao editarEvento(Integer id, Integer eventoId) {
        Inscricao inscricao = buscarInscricaoPeloID(id);
        Evento evento = eventoService.buscarEventoPeloID(eventoId);
        inscricao.setEvento(evento);
        return repository.save(inscricao);
    }

    public Inscricao buscarInscricaoPeloID(Integer id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<Inscricao> buscarInscricoes() {
        return repository.findAll();
    }

    public void removerInscricao(Integer id) {
        buscarInscricaoPeloID(id);
        repository.deleteById(id);
    }

    public void removerPorIdParticipante(Integer idParticipante) {
        repository.deleteAllByParticipante_Id(idParticipante);
    }

    public void removerPorIdEvento(Integer idEvento) {
        repository.deleteAllByEvento_Id(idEvento);
    }

    public List<Inscricao> buscarPorParticipante(Integer idParticipante) {
        return repository.findAllByParticipante_Id(idParticipante);
    }

    public List<Inscricao> buscarPorEvento(Integer idEvento) {
        return repository.findAllByEvento_Id(idEvento);
    }

    private Inscricao convertToEntity(InscricaoRequestDTO dto) {
        Inscricao inscricao = new Inscricao();
        inscricao.setEvento(eventoService.buscarEventoPeloID(dto.getEventoId()));
        inscricao.setParticipante(participanteService.buscarParticipantePeloID(dto.getParticipanteId()));
        return inscricao;
    }

}
