package net.weg.gestaoeventos.Service;

import lombok.AllArgsConstructor;
import net.weg.gestaoeventos.Entity.Inscricao;
import net.weg.gestaoeventos.Entity.Participante;
import net.weg.gestaoeventos.Repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ParticipanteService {

    private ParticipanteRepository participanteRepository;
    private InscricaoService inscricaoService;


    public Participante adicionarParticipante(Participante participante) {
        return participanteRepository.save(participante);
    }

    public Participante editarParticipante(Integer id, Participante participante) {
        participante.setId(id);
        return participanteRepository.save(participante);
    }

    public Participante buscarParticipantePeloID(Integer id) {

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

    public List<Participante> buscarParticipantes() {
        return participanteRepository.findAll();
    }

    public Participante buscarParticipantePeloEmail(String email) {
        Optional<Participante> participante = participanteRepository.findByEmail(email);
        if (participante.isPresent()) {
            return participante.get();
        }
        throw new NoSuchElementException();
    }

    public void editarEmail(Integer id, String email) {
        Participante participante = buscarParticipantePeloID(id);
        participante.setEmail(email);
        participanteRepository.save(participante);
    }

    public void removerParticipante(Integer id) {
        inscricaoService.removerPorIdParticipante(id);
        participanteRepository.deleteById(id);
    }



}
