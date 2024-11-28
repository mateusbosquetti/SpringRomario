package net.weg.gestaoeventos.Repository;

import net.weg.gestaoeventos.Entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Integer> {

}
