package net.weg.gestaoeventos.Controller;

import lombok.AllArgsConstructor;
import net.weg.gestaoeventos.Controller.dto.InscricaoRequestDTO;
import net.weg.gestaoeventos.Entity.Inscricao;
import net.weg.gestaoeventos.Service.InscricaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/inscricao")
@AllArgsConstructor
public class InscricaoController {

    private InscricaoService inscricaoService;

    @PostMapping
    public ResponseEntity<Inscricao> postInscricao(@RequestBody InscricaoRequestDTO dto) {
        try {
            return new ResponseEntity<>(inscricaoService.adicionarInscricao(dto), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inscricao> putInscricao(@PathVariable Integer id, @RequestBody InscricaoRequestDTO dto) {
        try {
            return new ResponseEntity<>(inscricaoService.editarInscricao(dto, id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PatchMapping("/")
    public ResponseEntity<Inscricao> patchEventoInscricao(@RequestParam Integer id, @RequestParam Integer eventoId) {
        try {
            return new ResponseEntity<>(inscricaoService.editarEvento(id, eventoId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscricao> getInscricaoById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(inscricaoService.buscarInscricaoPeloID(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Inscricao>> getInscricoes() {
        List<Inscricao> inscricoes = inscricaoService.buscarInscricoes();
        if (!inscricoes.isEmpty()) {
            return new ResponseEntity<>(inscricoes, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInscricao(@PathVariable Integer id) {
        try {
            inscricaoService.removerInscricao(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/participante/{idParticipante}")
    public ResponseEntity<List<Inscricao>> getInscricaoByParticipante(@PathVariable Integer idParticipante) {

        List<Inscricao> inscricoes = inscricaoService.buscarPorParticipante(idParticipante);
        if (!inscricoes.isEmpty()) {
            return new ResponseEntity<>(inscricoes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/evento/{idEvento}")
    public ResponseEntity<List<Inscricao>> getInscricaoByEvento(@PathVariable Integer idEvento) {
        List<Inscricao> inscricoes = inscricaoService.buscarPorEvento(idEvento);
        if (!inscricoes.isEmpty()) {
            return new ResponseEntity<>(inscricoes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
