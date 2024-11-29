package net.weg.gestaoeventos.Controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.weg.gestaoeventos.Entity.Evento;
import net.weg.gestaoeventos.Entity.Inscricao;
import net.weg.gestaoeventos.Entity.Participante;
import net.weg.gestaoeventos.Service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class InscricaoRequestDTO {
    private Evento evento;
    private Participante participante;

    public Inscricao conversao(){
        Inscricao inscricao = new Inscricao();
        inscricao.setEvento(this.getEvento());
        inscricao.setParticipante(this.getParticipante());
        return inscricao;
    }

}
