package br.edu.famper.agenciaviagem.model;

import br.edu.famper.agenciaviagem.model.Destino;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pacote")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pacote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", length = 150)
    private String nome;

    @Column(name = "descricao", length = 500)
    private String descricao;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "data_inicio")
    private Date dataInicio;

    @Column(name = "data_fim")
    private Date dataFim;

    @OneToMany(mappedBy = "pacote", targetEntity = Destino.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Destino> destinos;
}
