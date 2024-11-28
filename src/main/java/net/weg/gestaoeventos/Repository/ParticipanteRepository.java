package net.weg.gestaoeventos.Repository;

import net.weg.gestaoeventos.Entity.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {
    Optional<Participante> findByEmail(String email);

}
