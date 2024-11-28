package net.weg.gestaoeventos.Repository;

import net.weg.gestaoeventos.Entity.Inscricao;
import net.weg.gestaoeventos.Entity.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {

    //@Query para criar comando sql
    //Ele entende o que deve fazer pelo nome do método
    //O _ diz que você ta procurando dentro da classe
    List<Inscricao> findByParticipante_Id(Integer idParticipante);
}
