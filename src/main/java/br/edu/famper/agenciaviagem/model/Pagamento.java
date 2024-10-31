package br.edu.famper.agenciaviagem.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;

@Entity
@Table(name = "pagamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "data_pagamento")
    private Date dataPagamento;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "metodo_pagamento", length = 50)
    private String metodoPagamento;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;
}
