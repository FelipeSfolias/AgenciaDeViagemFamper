package br.edu.famper.agenciaviagem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "destino")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Destino {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome_destino", length = 150)
    private String nomeDestino;

    @Column(name = "pais", length = 100)
    private String pais;

    @Column(name = "cidade", length = 100)
    private String cidade;

    @Column(name = "descricao", length = 500)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "pacote_id")
    private Pacote pacote  ;
}
