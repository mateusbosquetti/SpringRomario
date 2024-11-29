package net.weg.gestaoeventos.Service;

import lombok.AllArgsConstructor;
import net.weg.gestaoeventos.Entity.Evento;
import net.weg.gestaoeventos.Entity.Participante;
import net.weg.gestaoeventos.Repository.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class EventoService {

    private EventoRepository repository;
    //private InscricaoService inscricaoService;

    public Evento adicionarEvento(Evento evento) {
        return repository.save(evento);
    }

    public Evento editarEvento(Integer id, Evento evento) {
        if (repository.existsById(id)) {
            evento.setId(id);
            return repository.save(evento);
        }
        throw new NoSuchElementException();
    }

    public Evento buscarEventoPeloID(Integer id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<Evento> buscarEventoPeloNome(String nome) {
        return repository.findByNome(nome);
    }

    public List<Evento> buscarEventos() {
        return repository.findAll();
    }

    public Evento editarData(Integer id, String data) {
        Evento evento = buscarEventoPeloID(id);
        evento.setData(data);
        return repository.save(evento);
    }

    public void removerEvento(Integer id) {
        if (repository.existsById(id)) {
            //inscricaoService.removerPorIdEvento(id);
            repository.deleteById(id);
        }
        throw new NoSuchElementException();
    }

}
