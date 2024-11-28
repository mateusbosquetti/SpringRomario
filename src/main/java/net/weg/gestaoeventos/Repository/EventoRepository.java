package net.weg.gestaoeventos.Repository;

import net.weg.gestaoeventos.Entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findByNome(String nome);
}
