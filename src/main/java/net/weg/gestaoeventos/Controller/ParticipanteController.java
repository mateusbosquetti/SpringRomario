package net.weg.gestaoeventos.Controller;

import lombok.AllArgsConstructor;
import net.weg.gestaoeventos.Entity.Participante;
import net.weg.gestaoeventos.Service.ParticipanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/participantes")
@AllArgsConstructor
public class ParticipanteController {

    private ParticipanteService participanteService;

    @PostMapping()
    public ResponseEntity<Participante> cadastroParticipante(@RequestBody Participante participante) {
        participante = participanteService.cadastro(participante);
        return new ResponseEntity<>(participante, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Participante> editarParticipante(@PathVariable Integer id, @RequestBody Participante participante) {
        participante = participanteService.editar(id, participante);
        return new ResponseEntity<>(participante, HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<Void> alterarEmail(@RequestParam Integer id, @RequestParam String email) {
        try {
            participanteService.alterarEmail(id, email);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerParticipante(@PathVariable Integer id) {
        participanteService.remover(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participante> buscarParticipattes(@PathVariable Integer id) {
        try {
            Participante participante = participanteService.buscar(id);
            return new ResponseEntity<>(participante, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Participante>> buscarParticipantes(@PathVariable Integer id) {
        List<Participante> participante = participanteService.buscar();
        if (!participante.isEmpty()) {
            return new ResponseEntity<>(participante, HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
