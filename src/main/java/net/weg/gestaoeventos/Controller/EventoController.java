package net.weg.gestaoeventos.Controller;

import lombok.AllArgsConstructor;
import net.weg.gestaoeventos.Entity.Evento;
import net.weg.gestaoeventos.Repository.EventoRepository;
import net.weg.gestaoeventos.Service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/evento")
@AllArgsConstructor
public class EventoController {

    private EventoService eventoService;
    //.save, caso ja haja um item no bd com o msm id, ele só atualiza, senao ele adiciona

    @PostMapping()
    public ResponseEntity<Evento> postEvento(@RequestBody Evento evento) {
        try {
            return new ResponseEntity<>(eventoService.adicionarEvento(evento), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@RequestBody Evento evento, @PathVariable Integer id) {
        try {
            return new ResponseEntity<>(eventoService.editarEvento(id, evento), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Integer id) {
        try {
            eventoService.removerEvento(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PatchMapping()
    public ResponseEntity<Evento> patchDataEvento(@RequestParam(name = "") Integer id, @RequestParam String data) {
        try {
            return new ResponseEntity<>(eventoService.editarData(id, data), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(eventoService.buscarEventoPeloID(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/getAll/nome/{nome}")
    public ResponseEntity<List<Evento>> getEventosByName(@PathVariable String nome) {
        List<Evento> eventos = eventoService.buscarEventoPeloNome(nome);
        if (!eventos.isEmpty()) {
            return new ResponseEntity<>(eventos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Evento>> getEventos() {
        List<Evento> eventos = eventoService.buscarEventos();
        if (!eventos.isEmpty()) {
            return new ResponseEntity<>(eventos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
