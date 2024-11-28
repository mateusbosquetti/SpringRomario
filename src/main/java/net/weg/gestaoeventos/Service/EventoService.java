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

    public Evento cadastro(Evento evento) {
        return repository.save(evento);
    }

    public Evento editar(Integer id, Evento evento) {
        evento.setId(id);
        return repository.save(evento);
    }

    public Evento buscar(Integer id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<Evento> buscar() {
        return repository.findAll();
    }

    public void alterarData(Integer id, String data) {
        Evento evento = buscar(id);
        evento.setData(data);
        repository.save(evento);
    }

    public void remover(Integer id) {
        repository.deleteById(id);
    }


}
