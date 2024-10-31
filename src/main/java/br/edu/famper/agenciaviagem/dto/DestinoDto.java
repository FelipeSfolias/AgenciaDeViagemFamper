package br.edu.famper.agenciaviagem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
