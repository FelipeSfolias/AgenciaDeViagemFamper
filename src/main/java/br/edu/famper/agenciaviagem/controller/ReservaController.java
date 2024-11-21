package br.edu.famper.agenciaviagem.controller;
import br.edu.famper.agenciaviagem.dto.ReservaDto;
import br.edu.famper.agenciaviagem.exception.ResourceNotFoundException;
import br.edu.famper.agenciaviagem.model.Reserva;
import br.edu.famper.agenciaviagem.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/reserva")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Reserva",
        description = "Operação com as Reservas de Viagem")
public class ReservaController {

    private final ReservaService reservaService;

    // Endpoint para obter todas as reserva
    @GetMapping
    @ResponseStatus(HttpStatus.OK)// Define o status HTTP 200 para este endpoint
    @Operation(summary = "Busca todas as Reservas",
            description = "Busca todas as reservas do Banco de Dados e retorna " +
                    "um array em formato JSON"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "FOUND"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public List<ReservaDto> getAllReservas() {
        log.info("Buscando todas as reservas");
        return reservaService.getAllReservas();
    }



    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca uma reserva",
            description = "Busca uma reserva específica do Banco de Dados e retorna" +
                    "um objeto em formato JSON"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "FOUND"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    // Endpoint para obter uma reserva por ID
    public ResponseEntity<ReservaDto> getReservaById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando reserva por id: {}", id);
        return ResponseEntity.ok().body(reservaService.getReservaById(id));
    }

    // Endpoint para criar uma nova reserva
    @PostMapping
    @Operation(summary = "Salva Reserva",
            description = "Salva uma reserva no Banco de Dados"
    )
    public Reserva createReserva(@RequestBody ReservaDto reservaDto) throws ResourceNotFoundException {
        log.info("Cadastro de reservas: {}", reservaDto);
        return reservaService.saveReserva(reservaDto);
    }

    // Endpoint para atualizar uma reserva
    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza uma Reserva",
            description = "Atualiza uma reserva no Banco de Dados"
    )

    public ResponseEntity<ReservaDto> updateReserva(@PathVariable(name = "id") Long id, @RequestBody ReservaDto reservaDto) throws ResourceNotFoundException {
        log.info("Atualizando reserva: {}", reservaDto);
        return ResponseEntity.ok(reservaService.editReserva(id, reservaDto));
    }


    // Endpoint para deletar uma reserva
    @DeleteMapping("/{id}")
    @Operation(summary = "Remover Reserva",
            description = "Remove uma reserva no banco de Dados"
    )
    public Map<String, Boolean> deleteReserva(@PathVariable(name = "id") Long id) throws Exception {
        log.info("Reserva cancelada: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", reservaService.deleteReserva(id));
        return response;
    }
}

