package net.weg.gestaoeventos.Repository;

import net.weg.gestaoeventos.Entity.Inscricao;
import net.weg.gestaoeventos.Entity.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {
}
