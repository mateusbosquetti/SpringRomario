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

    @PostMapping("/save")
    public ResponseEntity<Evento> postEvento(@RequestBody Evento evento) {
        try {
            return new ResponseEntity<>(eventoService.adicionarEvento(evento), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Evento> updateEvento(@RequestBody Evento evento, @PathVariable Integer id) {
        try {
            return new ResponseEntity<>(eventoService.editarEvento(id, evento), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Integer id) {
        try {
            eventoService.removerEvento(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PatchMapping("/patch")
    public ResponseEntity<Evento> patchDataEvento(@RequestParam(name = "") Integer id, @RequestParam String data) {
        try {
            return new ResponseEntity<>(eventoService.editarData(id, data), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Integer id) {
        return eventoService.buscarEventoPeloID(id);
    }

    @GetMapping("/getAll/nome/{nome}")
    public ResponseEntity<List<Evento>> getEventosByName(@PathVariable String nome) {
        return eventoService.buscarEventoPeloNome(nome);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Evento>> getEventos() {
        return eventoService.buscarEventos();
    }

}
