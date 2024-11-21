package br.edu.famper.agenciaviagem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Date;

@Getter  // Gera automaticamente os métodos getters para todos os campos
@Setter  // Gera automaticamente os métodos setters para todos os campos
@AllArgsConstructor  // Gera um construtor com todos os campos como parâmetros
@NoArgsConstructor  // Gera um construtor vazio
@Builder  // Habilita o padrão de projeto Builder para instanciar objetos de ClienteDto
public class ReservaDto {

    @Schema(description = "Data da Reserva", example = "2024-01-01")
    private Date dataReserva;

    @Schema(description = "Status da Reserva", example = "Confirmada", maxLength = 50)
    private String status;

    @Schema(description = "ID do Cliente", example = "1")
    private Long clienteId;

    @Schema(description = "ID do Pacote de Viagem", example = "1")
    private Long pacoteViagemId;
}
