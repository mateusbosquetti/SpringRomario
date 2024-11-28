package net.weg.gestaoeventos.Controller;

import lombok.AllArgsConstructor;
import net.weg.gestaoeventos.Entity.Evento;
import net.weg.gestaoeventos.Repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evento")
@AllArgsConstructor
public class EventoController {

    private EventoRepository eventoRepository;

    @PostMapping("/save")
    public Evento saveEvento(@RequestBody Evento evento){
        return eventoRepository.save(evento);
        //.save, caso ja haja um item no bd com o msm id, ele só atualiza, senao ele adiciona
    }

    @PutMapping("/edit")
    public Evento updateEvento(@RequestBody Evento evento){
        return eventoRepository.save(evento);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEvento(@PathVariable Integer id){
        eventoRepository.deleteById(id);
//        eventoRepository.delete(eventoRepository.findById(id).get());
    }

    @PatchMapping("/patch")
    public void updateLocalEvento(@RequestParam(name = "") Integer id, @RequestParam String data){
        Optional<Evento> eventoOptional = eventoRepository.findById(id);
        Evento evento = eventoOptional.get();
        evento.setData(data);
        eventoRepository.save(evento);
    }

    @GetMapping("/get/{id}")
    public Evento getEventoById(@PathVariable Integer id){
        Optional<Evento> optionalEvento = eventoRepository.findById(id);
        if (optionalEvento.isPresent()){
            return optionalEvento.get();
        }
        throw new RuntimeException();
    }

    @GetMapping("/getAll")
    public List<Evento> getEventoById(){
        return eventoRepository.findAll();
    }

}
