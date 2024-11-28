package net.weg.gestaoeventos.Repository;

import net.weg.gestaoeventos.Entity.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {
}
