package br.edu.famper.agenciaviagem.controller;

import br.edu.famper.agenciaviagem.dto.DestinoDto;
import br.edu.famper.agenciaviagem.exception.ResourceNotFoundException;
import br.edu.famper.agenciaviagem.model.Destino;
import br.edu.famper.agenciaviagem.model.Pacote;
import br.edu.famper.agenciaviagem.service.DestinoService;
import br.edu.famper.agenciaviagem.service.PacoteService;
import br.edu.famper.agenciaviagem.dto.PacoteDto;
import br.edu.famper.agenciaviagem.service.PacoteService;
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
@RequestMapping("/api/pacote")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Pacote",
        description = "Operação com os Pacotes de Viagem")
public class PacoteController {

    private final PacoteService pacoteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca todos os Pacotes",
            description = "Busca todos os pacotes do Banco de Dados e retorna " +
                    "um array em formato JSON"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "FOUND"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public List<PacoteDto> getAllPacotes() {
        log.info("Buscando todas os pacotes");
        return pacoteService.getAllPacotes();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca um pacote",
            description = "Busca um pacote específico do Banco de Dados e retorna" +
                    "um objeto em formato JSON"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "FOUND"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ResponseEntity<PacoteDto> getPacoteById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando pacote por id: {}", id);
        return ResponseEntity.ok().body(pacoteService.getPacoteById(id));
    }

    @PostMapping
    @Operation(summary = "Salva Pacote ",
            description = "Salva um pacote no Banco de Dados"
    )
    public Pacote createPacote(@RequestBody PacoteDto pacoteDto) throws ResourceNotFoundException {
        log.info("Cadastro de pacotes: {}", pacoteDto);
        return pacoteService.savePacote(pacoteDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza um Pacote",
            description = "Atualiza um pacote no Banco de Dados"
    )
    public ResponseEntity<PacoteDto> updatePacote(@PathVariable(name = "id") Long id, @RequestBody PacoteDto pacoteDto) throws ResourceNotFoundException {
        log.info("Atualizando pacotes: {}", pacoteDto);
        return ResponseEntity.ok(pacoteService.editPacote(id, pacoteDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover Pacote",
            description = "Remove um Pacote no banco de Dados"
    )
    public Map<String, Boolean> deletePacote(@PathVariable(name = "id") Long id) throws Exception {
        log.info("Deletando pacote: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", pacoteService.deletePacote(id));
        return response;
    }


}