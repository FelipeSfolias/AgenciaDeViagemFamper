package br.edu.famper.agenciaviagem.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagamentoDto {

    @Schema(description = "Data do Pagamento", example = "2024-01-05")
    private Date dataPagamento;

    @Schema(description = "Valor do Pagamento", example = "1500.00")
    private Double valor;

    @Schema(description = "Método de Pagamento", example = "Cartão de Crédito", maxLength = 50)
    private String metodoPagamento;

    @Schema(description = "ID da Reserva", example = "1")
    private Long reservaId;
}
