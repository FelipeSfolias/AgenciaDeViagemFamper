package br.edu.famper.agenciaviagem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity  // Indica que esta classe é uma entidade JPA, mapeada para uma tabela no banco de dados
@Table(name = "cliente")  // Define o nome da tabela no banco de dados como "cliente"
@Getter  // Gera automaticamente os métodos getters para todos os campos
@Setter  // Gera automaticamente os métodos setters para todos os campos
@AllArgsConstructor  // Gera um construtor com todos os campos como parâmetros
@NoArgsConstructor  // Gera um construtor vazio
@EqualsAndHashCode(of = "id")  // Gera os métodos equals e hashCode, considerando apenas o campo "id"
public class Cliente {

    @Id  // Indica que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.AUTO)  // Define a geração automática do valor da chave primária
    private Long id;

    @Column(name = "nome", length = 150)  // Mapeia o campo "nome" com um limite de 150 caracteres
    private String nome;

    @Column(name = "cpf", length = 11, unique = true)
    // Mapeia o campo "cpf" com um limite de 11 caracteres e valor único
    private String cpf;

    @Column(name = "email", length = 100)  // Mapeia o campo "email" com um limite de 100 caracteres
    private String email;

    @Column(name = "telefone", length = 15)  // Mapeia o campo "telefone" com um limite de 15 caracteres
    private String telefone;

    @Column(name = "endereco", length = 250)  // Mapeia o campo "endereco" com um limite de 250 caracteres
    private String endereco;
}
