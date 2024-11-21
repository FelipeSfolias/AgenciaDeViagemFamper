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


@RestController // Anotação que indica que esta classe é um controlador REST.
@RequestMapping("/api/destino")// Define a URL base para os endpoints deste controlador.
@RequiredArgsConstructor// Anotação Lombok que gera um construtor com todos os atributos finais (injetáveis).
@Slf4j// Anotação Lombok para habilitar logging com slf4j.
@Tag(name = "Destino", description = "Operação de Destinos")  // Anotação Swagger para documentar este controlador.

public class DestinoController {

    // Injeção de dependência do serviço DestinoService.
    private final DestinoService destinoService;

    // Endpoint para obter todos os destinos.  GET /api/destino
    @GetMapping
    @ResponseStatus(HttpStatus.OK) // Define o código de status HTTP como 200 (OK).
    @Operation(summary = "Busca todos os Destinos", description = "Busca todos os destinos do Banco de Dados e retorna um array em formato JSON")
    // Documentação Swagger
    @ApiResponses(value = { // Documentação Swagger para diferentes códigos de resposta
            @ApiResponse(responseCode = "200", description = "FOUND"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public List<DestinoDto> getallDestinos() {
        log.info("Buscando todas os destinos"); // Registra um log de informação.
        return destinoService.getAllDestinos(); // Chama o método do serviço para buscar todos os destinos.
    }

    // Endpoint para obter um destino por ID. GET /api/destino/{id}
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca um destino", description = "Busca um destino específico do Banco de Dados e retorna um objeto em formato JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "FOUND"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ResponseEntity<DestinoDto> getDestinoById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando destino por id: {}", id); // Registra um log de informação com o ID.
        return ResponseEntity.ok().body(destinoService.getDestinoById(id)); // Retorna o destino em um ResponseEntity.
    }

    // Endpoint para criar um novo destino. POST /api/destino
    @PostMapping
    @Operation(summary = "Salva Destino", description = "Salva uma cidade no Banco de Dados")
    // A descrição está incorreta, deveria ser "Salva um destino..."
    public Destino createDestino(@RequestBody DestinoDto destinoDto) throws ResourceNotFoundException {
        log.info("Cadastro destinos: {}", destinoDto); // Registra um log de informação com os dados do destino.
        return destinoService.saveDestino(destinoDto); // Chama o método do serviço para salvar o destino.
    }

    // Endpoint para atualizar um destino. PATCH /api/destino/{id}
    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza um Destino", description = "Atualiza um Destino no Banco de Dados")
    public ResponseEntity<DestinoDto> updateDestino(@PathVariable(name = "id") Long id, @RequestBody DestinoDto destinoDto) throws ResourceNotFoundException {
        log.info("Atualizando destino: {}", destinoDto); // Registra um log de informação com os dados do destino.
        return ResponseEntity.ok(destinoService.editDestino(id, destinoDto)); // Retorna o destino atualizado em um ResponseEntity.
    }

    // Endpoint para deletar um destino. DELETE /api/destino/{id}
    @DeleteMapping("/{id}")
    @Operation(summary = "Remover Destino", description = "Remove um Destino no banco de Dados")
    public Map<String, Boolean> deleteDestino(@PathVariable(name = "id") Long id) throws Exception {
        log.info("Deletando destino: {}", id); // Registra um log de informação com o ID.
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", destinoService.deleteDestino(id)); // Chama o método do serviço para deletar o destino.
        return response;
    }
}
