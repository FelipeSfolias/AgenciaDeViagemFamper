package br.edu.famper.agenciaviagem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter  // Gera automaticamente os métodos getters para todos os campos
@Setter  // Gera automaticamente os métodos setters para todos os campos
@AllArgsConstructor  // Gera um construtor com todos os campos como parâmetros
@NoArgsConstructor  // Gera um construtor vazio
@Builder  // Habilita o padrão de projeto Builder para instanciar objetos de ClienteDto
public class DestinoDto {

    @Schema(description = "Nome do Destino", example = "Paris", maxLength = 150)
    private String nomeDestino;

    @Schema(description = "País do Destino", example = "França", maxLength = 100)
    private String pais;

    @Schema(description = "Cidade do Destino", example = "Paris", maxLength = 100)
    private String cidade;

    @Schema(description = "Descrição do Destino", example = "Uma cidade famosa pelo seu charme e pela Torre Eiffel.", maxLength = 500)
    private String descricao;
}
