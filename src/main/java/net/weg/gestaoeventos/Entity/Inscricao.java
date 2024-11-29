    package net.weg.gestaoeventos.Entity;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotNull;
    import lombok.Data;

    @Entity
    @Data
    @Table(name = "inscricao")
    public class Inscricao {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @ManyToOne
        @NotNull
        private Evento evento;
        @ManyToOne
        @NotNull
        private Participante participante;

    }
