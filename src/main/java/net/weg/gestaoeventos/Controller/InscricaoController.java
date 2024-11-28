package net.weg.gestaoeventos.Controller;

import net.weg.gestaoeventos.Controller.dto.InscricaoRequestDTO;
import net.weg.gestaoeventos.Entity.Inscricao;
import net.weg.gestaoeventos.Service.InscricaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("inscricao")
public class InscricaoController {

    private InscricaoService inscricaoService;

    @PostMapping
    public ResponseEntity<Inscricao> cadastro(@RequestBody InscricaoRequestDTO dto) {
        try {
            Inscricao inscricao = inscricaoService.cadastro(dto);
            return new ResponseEntity<>(inscricao, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inscricao> edicao(@PathVariable Integer id, @RequestBody InscricaoRequestDTO dto) {
        try {
            Inscricao inscricao = inscricaoService.edicao(dto, id);
            return new ResponseEntity<>(inscricao, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/")
    public ResponseEntity<Inscricao> alterarEvento(@RequestParam Integer id, @RequestParam Integer eventoId) {
        try {
            Inscricao inscricao = inscricaoService.trocarEvento(id, eventoId);
            return new ResponseEntity<>(inscricao, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscricao> buscarInscricao(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(inscricaoService.buscarUmaInscricao(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
