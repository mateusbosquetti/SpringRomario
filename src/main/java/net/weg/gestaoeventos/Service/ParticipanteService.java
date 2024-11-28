package net.weg.gestaoeventos.Service;

import net.weg.gestaoeventos.Entity.Participante;
import net.weg.gestaoeventos.Repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ParticipanteService {

    @Autowired
    private ParticipanteRepository participanteRepository;


    public Participante cadastro(Participante participante) {
        return participanteRepository.save(participante);
    }

    public Participante editar(Integer id, Participante participante) {
        participante.setId(id);
        return participanteRepository.save(participante);
    }

    public Participante buscar(Integer id) {

        return participanteRepository.findById(id).orElseThrow(NoSuchElementException::new);
        //Outro modo
        /*
        Optional<Participante> optional = participanteRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NoSuchElementException();
         */

    }

    public List<Participante> buscar() {
        return participanteRepository.findAll();
    }

    public void alterarEmail(Integer id, String email) {
        Participante participante = buscar(id);
        participante.setEmail(email);
        participanteRepository.save(participante);
    }

    public void remover(Integer id) {
        participanteRepository.deleteById(id);
    }

    public Participante buscar(String email){
        Optional<Participante> participante = participanteRepository.findByEmail(email);
        if (participante.isPresent()){
            return participante.get();
        }
        throw new NoSuchElementException();
    }

}
