package br.edu.famper.agenciaviagem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity  // Indica que esta classe é uma entidade JPA, mapeada para uma tabela no banco de dados
@Table(name = "destino")// Define o nome da tabela no banco de dados como "destino"
@Getter  // Gera automaticamente os métodos getters para todos os campos
@Setter  // Gera automaticamente os métodos setters para todos os campos
@AllArgsConstructor  // Gera um construtor com todos os campos como parâmetros
@NoArgsConstructor  // Gera um construtor vazio
@EqualsAndHashCode(of = "id")  // Gera os métodos equals e hashCode, considerando apenas o campo "id"
public class Destino {
    @Id// Indica que este campo é a chave primária
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
