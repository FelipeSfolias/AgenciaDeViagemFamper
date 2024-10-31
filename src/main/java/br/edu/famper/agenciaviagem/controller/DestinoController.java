package br.edu.famper.agenciaviagem.controller;


import br.edu.famper.agenciaviagem.dto.DestinoDto;
import br.edu.famper.agenciaviagem.exception.ResourceNotFoundException;
import br.edu.famper.agenciaviagem.model.Destino;
import br.edu.famper.agenciaviagem.service.DestinoService;
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
@RequestMapping("/api/destino")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Destino",
        description = "Operação de Destinos")
public class DestinoController {

    private final DestinoService destinoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca todos os Destinos",
            description = "Busca todos os destinos do Banco de Dados e retorna " +
                    "um array em formato JSON"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "FOUND"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public List<DestinoDto> getallDestinos() {
        log.info("Buscando todas os destinos");
        return destinoService.getAllDestinos();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca um destino",
            description = "Busca um destino específico do Banco de Dados e retorna" +
                    "um objeto em formato JSON"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "FOUND"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ResponseEntity<DestinoDto> getDestinoById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando destino por id: {}", id);
        return ResponseEntity.ok().body(destinoService.getDestinoById(id));
    }

    @PostMapping
    @Operation(summary = "Salva Destino ",
            description = "Salva uma cidade no Banco de Dados"
    )
    public Destino createDestino(@RequestBody DestinoDto destinoDto) throws ResourceNotFoundException {
        log.info("Cadastro destinos: {}", destinoDto);
        return destinoService.saveDestino(destinoDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza um Destino",
            description = "Atualiza um Destino no Banco de Dados"
    )
    public ResponseEntity<DestinoDto> updateDestino(@PathVariable(name = "id") Long id, @RequestBody DestinoDto destinoDto) throws ResourceNotFoundException {
        log.info("Atualizando destino: {}", destinoDto);
        return ResponseEntity.ok(destinoService.editDestino(id, destinoDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover Destino",
            description = "Remove um Destino no bancoo de Dados"
    )
    public Map<String, Boolean> deleteDestino(@PathVariable(name = "id") Long id) throws Exception {
        log.info("Deletando destino: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", destinoService.deleteDestino(id));
        return response;
    }


}