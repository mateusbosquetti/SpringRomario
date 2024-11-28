    package net.weg.gestaoeventos.Entity;

    import jakarta.persistence.*;
    import lombok.Data;

    @Entity
    @Data
    @Table(name = "inscricao")
    public class Inscricao {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @ManyToOne
        private Evento evento;
        @ManyToOne
        private Participante participante;

    }
