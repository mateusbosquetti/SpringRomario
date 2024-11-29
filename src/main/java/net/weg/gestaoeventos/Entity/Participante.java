package net.weg.gestaoeventos.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "participante")
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    private String name;
    private String email;

    //Um participante pode ter muitas inscrições
    //O relacionamento está sobre responsabilidade do atributo eveno
    @OneToMany(mappedBy = "participante")
    @JsonIgnore
    @ToString.Exclude
    private List<Inscricao> inscricoes = new ArrayList<>();
}
