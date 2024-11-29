package net.weg.gestaoeventos.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //AUTO: Automatico, vai consultar qual o banco de dados (mysql), no mysql o tipo padrao é o SEQUENCE
    //IDENTITY: identificador daquele registro, vai criar um valor unico dentro daquela tabela, não reutiliza ID
    //TABLE: igual o identity, porem ele reaproveita ID's, se eu apagar o 1, quando eu for criar um novo ele volta a usar o 1
    //SEQUENCE: No escopo do banco, ignora as tabelas, todas as tabelas nao repetem os valores
    //UUID: Identificador unico no mundo inteiro

    private String nome;
    private String local;
    private String data;
    private String descricao;

    //Um evento pode ter muitas inscricoes
    //"As incricoes estão sobre responsabilidade do atributo evento"
    @OneToMany(mappedBy = "evento")
    @JsonIgnore
    @ToString.Exclude
    private List<Inscricao> inscricoes = new ArrayList<>();

}
//GET pegar
//POST salvar
//DELETE deletar
//PUT editar completamente
//PATCH editar poucos dados