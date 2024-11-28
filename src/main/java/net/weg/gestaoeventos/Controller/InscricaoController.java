package net.weg.gestaoeventos.Controller;

import net.weg.gestaoeventos.Controller.dto.InscricaoRequestDTO;
import net.weg.gestaoeventos.Entity.Inscricao;
import net.weg.gestaoeventos.Service.InscricaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("inscricao")
public class InscricaoController {

    private InscricaoService inscricaoService;

    @PostMapping
    public ResponseEntity<Inscricao> cadastro(@RequestBody InscricaoRequestDTO dto) {
        try {
            Inscricao inscricao = inscricaoService.adicionarInscricao(dto);
            return new ResponseEntity<>(inscricao, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inscricao> edicao(@PathVariable Integer id, @RequestBody InscricaoRequestDTO dto) {
        try {
            Inscricao inscricao = inscricaoService.editarInscricao(dto, id);
            return new ResponseEntity<>(inscricao, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/")
    public ResponseEntity<Inscricao> alterarEvento(@RequestParam Integer id, @RequestParam Integer eventoId) {
        try {
            Inscricao inscricao = inscricaoService.editarEvento(id, eventoId);
            return new ResponseEntity<>(inscricao, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscricao> buscarInscricao(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(inscricaoService.buscarInscricaoPeloID(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Inscricao>> buscar() {
        return new ResponseEntity<>(inscricaoService.buscarInscricoes(), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            inscricaoService.removerInscricao(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/participante/{idParticipante}")
    public ResponseEntity<List<Inscricao>> buscarInscricaoByParticipante(@PathVariable Integer idParticipante) {

        List<Inscricao> inscricoes = inscricaoService.buscarPorParticipante(idParticipante);
        if (inscricoes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(inscricoes, HttpStatus.OK);
        }
    }

    @GetMapping("/evento/{idEvento}")
    public ResponseEntity<List<Inscricao>> buscarInscricaoByEvento(@PathVariable Integer idEvento) {
        List<Inscricao> inscricoes = inscricaoService.buscarPorEvento(idEvento);
        if (inscricoes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(inscricoes, HttpStatus.OK);
        }
    }


}
