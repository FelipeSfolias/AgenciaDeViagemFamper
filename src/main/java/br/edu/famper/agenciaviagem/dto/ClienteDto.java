package br.edu.famper.agenciaviagem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter  // Gera automaticamente os métodos getters para todos os campos
@Setter  // Gera automaticamente os métodos setters para todos os campos
@AllArgsConstructor  // Gera um construtor com todos os campos como parâmetros
@NoArgsConstructor  // Gera um construtor vazio
@Builder  // Habilita o padrão de projeto Builder para instanciar objetos de ClienteDto
public class ClienteDto {

    @Schema(description = "Nome do Cliente", example = "João da Silva", maxLength = 150)
    private String nome;

    @Schema(description = "CPF do Cliente", example = "12345678900", maxLength = 11)
    private String cpf;

    @Schema(description = "Email do Cliente", example = "joao.silva@example.com", maxLength = 100)
    private String email;

    @Schema(description = "Telefone do Cliente", example = "(41) 99999-9999", maxLength = 15)
    private String telefone;

    @Schema(description = "Endereço do Cliente", example = "Rua das Flores, 123", maxLength = 250)
    private String endereco;
}
