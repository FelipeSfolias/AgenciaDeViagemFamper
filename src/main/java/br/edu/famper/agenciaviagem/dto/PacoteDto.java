package br.edu.famper.agenciaviagem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Date;

@Getter  // Gera automaticamente os métodos getters para todos os campos
@Setter  // Gera automaticamente os métodos setters para todos os campos
@AllArgsConstructor  // Gera um construtor com todos os campos como parâmetros
@NoArgsConstructor  // Gera um construtor vazio
@Builder  // Habilita o padrão de projeto Builder para instanciar objetos de ClienteDto
public class PacoteDto {

    @Schema(description = "Nome do Pacote de Viagem", example = "Pacote de Verão", maxLength = 150)
    private String nome;

    @Schema(description = "Descrição do Pacote de Viagem", example = "Pacote completo para aproveitar o verão", maxLength = 500)
    private String descricao;

    @Schema(description = "Preço do Pacote", example = "1500.00")
    private Double preco;

    @Schema(description = "Data de Início do Pacote", example = "2024-01-10")
    private Date dataInicio;

    @Schema(description = "Data de Fim do Pacote", example = "2024-01-20")
    private Date dataFim;

    @Schema(description = "ID do Destino", example = "1")
    private Long destinoId;
}
