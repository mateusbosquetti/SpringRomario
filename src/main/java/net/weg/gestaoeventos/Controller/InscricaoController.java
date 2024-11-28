package net.weg.gestaoeventos.Controller;

import net.weg.gestaoeventos.Controller.dto.InscricaoRequestDTO;
import net.weg.gestaoeventos.Entity.Inscricao;
import net.weg.gestaoeventos.Service.InscricaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Inscricao> edicao(@PathVariable Integer id, @RequestBody InscricaoRequestDTO dto){
        try {
            Inscricao inscricao = inscricaoService.edicao(dto);
            return new ResponseEntity<>(inscricao, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
