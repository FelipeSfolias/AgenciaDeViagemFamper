package br.edu.famper.agenciaviagem.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;

@Entity  // Indica que esta classe é uma entidade JPA, mapeada para uma tabela no banco de dados
@Table(name = "pagamento")// Define o nome da tabela no banco de dados como "pagamento"
@Getter  // Gera automaticamente os métodos getters para todos os campos
@Setter  // Gera automaticamente os métodos setters para todos os campos
@AllArgsConstructor  // Gera um construtor com todos os campos como parâmetros
@NoArgsConstructor  // Gera um construtor vazio
@EqualsAndHashCode(of = "id")  // Gera os métodos equals e hashCode, considerando apenas o campo "id"
public class Pagamento {
    @Id// Indica que este campo é a chave primária
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
