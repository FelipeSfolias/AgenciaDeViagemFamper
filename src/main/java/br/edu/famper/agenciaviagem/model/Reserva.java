package br.edu.famper.agenciaviagem.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity  // Indica que esta classe é uma entidade JPA, mapeada para uma tabela no banco de dados
@Table(name = "reserva")// Define o nome da tabela no banco de dados como "reserva"
@Getter  // Gera automaticamente os métodos getters para todos os campos
@Setter  // Gera automaticamente os métodos setters para todos os campos
@AllArgsConstructor  // Gera um construtor com todos os campos como parâmetros
@NoArgsConstructor  // Gera um construtor vazio
@EqualsAndHashCode(of = "id")  // Gera os métodos equals e hashCode, considerando apenas o campo "id"
public class Reserva {
    @Id// Indica que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "data_reserva")
    private Date dataReserva;

    @Column(name = "status", length = 50)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pacote_id")
    private Pacote pacote;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pagamento_id")
    private Pagamento pagamento;
}
