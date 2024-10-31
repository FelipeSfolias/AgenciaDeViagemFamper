package br.edu.famper.agenciaviagem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", length = 150)
    private String nome;

    @Column(name = "cpf", length = 11, unique = true)
    private String cpf;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "telefone", length = 15)
    private String telefone;

    @Column(name = "endereco", length = 250)
    private String endereco;
}
