package br.edu.famper.agenciaviagem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import java.util.Set;

@Entity  // Indica que esta classe é uma entidade JPA, mapeada para uma tabela no banco de dados
@Table(name = "pacote")// Define o nome da tabela no banco de dados como "pacote"
@Getter  // Gera automaticamente os métodos getters para todos os campos
@Setter  // Gera automaticamente os métodos setters para todos os campos
@AllArgsConstructor  // Gera um construtor com todos os campos como parâmetros
@NoArgsConstructor  // Gera um construtor vazio
@EqualsAndHashCode(of = "id")  // Gera os métodos equals e hashCode, considerando apenas o campo "id"
public class Pacote {
    @Id// Indica que este campo é a chave primária
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
