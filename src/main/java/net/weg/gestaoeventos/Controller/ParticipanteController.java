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
@RequestMapping("/participante")
@AllArgsConstructor
public class ParticipanteController {

    private ParticipanteService participanteService;

    @PostMapping()
    public ResponseEntity<Participante> cadastroParticipante(@RequestBody Participante participante) {
        try {
            return new ResponseEntity<>(participanteService.adicionarParticipante(participante), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participante> editarParticipante(@PathVariable Integer id, @RequestBody Participante participante) {
        try {
            return new ResponseEntity<>(participanteService.editarParticipante(id, participante), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PatchMapping()
    public ResponseEntity<Void> alterarEmail(@RequestParam Integer id, @RequestParam String email) {
        try {
            participanteService.editarEmail(id, email);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerParticipante(@PathVariable Integer id) {
        try {
            participanteService.removerParticipante(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participante> buscarParticipantes(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(participanteService.buscarParticipantePeloID(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Participante>> buscarParticipantes() {
        List<Participante> participante = participanteService.buscarParticipantes();
        if (!participante.isEmpty()) {
            return new ResponseEntity<>(participante, HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/email")
    public ResponseEntity<Participante> buscarParticipantes(@RequestParam String email) {
        try {
            return new ResponseEntity<>(participanteService.buscarParticipantePeloEmail(email), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
