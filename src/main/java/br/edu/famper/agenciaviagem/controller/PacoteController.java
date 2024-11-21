package br.edu.famper.agenciaviagem.controller;

import br.edu.famper.agenciaviagem.exception.ResourceNotFoundException;
import br.edu.famper.agenciaviagem.model.Pacote;
import br.edu.famper.agenciaviagem.service.PacoteService;
import br.edu.famper.agenciaviagem.dto.PacoteDto;
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


@RestController// Anotação que indica que esta classe é um controlador REST.
@RequestMapping("/api/pacote")// Define a URL base para os endpoints deste controlador.
@RequiredArgsConstructor// Anotação Lombok que gera um construtor com todos os atributos finais (injetáveis).
@Slf4j// Anotação Lombok para habilitar logging com slf4j.
@Tag(name = "Pacote", description = "Operação com os Pacotes de Viagem")
// Anotação Swagger para documentar este controlador.
public class PacoteController {

    // Injeção de dependência do serviço PacoteService.
    private final PacoteService pacoteService;

    // Endpoint para obter todos os pacotes. GET /api/pacote
    @GetMapping
    @ResponseStatus(HttpStatus.OK) // Define o código de status HTTP como 200 (OK).
    @Operation(summary = "Busca todos os Pacotes", description = "Busca todos os pacotes do Banco de Dados e retorna um array em formato JSON")
    // Documentação Swagger
    @ApiResponses(value = { // Documentação Swagger para diferentes códigos de resposta
            @ApiResponse(responseCode = "200", description = "FOUND"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public List<PacoteDto> getAllPacotes() {
        log.info("Buscando todos os pacotes"); // Registra um log de informação.
        return pacoteService.getAllPacotes(); // Chama o método do serviço para buscar todos os pacotes.
    }

    // Endpoint para obter um pacote por ID. GET /api/pacote/{id}
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca um pacote", description = "Busca um pacote específico do Banco de Dados e retorna um objeto em formato JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "FOUND"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ResponseEntity<PacoteDto> getPacoteById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando pacote por id: {}", id); // Registra um log de informação com o ID.
        return ResponseEntity.ok().body(pacoteService.getPacoteById(id)); // Retorna o pacote em um ResponseEntity.
    }

    // Endpoint para criar um novo pacote. POST /api/pacote
    @PostMapping
    @Operation(summary = "Salva Pacote", description = "Salva um pacote no Banco de Dados")
    public Pacote createPacote(@RequestBody PacoteDto pacoteDto) throws ResourceNotFoundException {
        log.info("Cadastro de pacotes: {}", pacoteDto); // Registra um log de informação com os dados do pacote.
        return pacoteService.savePacote(pacoteDto); // Chama o método do serviço para salvar o pacote.
    }

    // Endpoint para atualizar um pacote. PATCH /api/pacote/{id}
    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza um Pacote", description = "Atualiza um pacote no Banco de Dados")
    public ResponseEntity<PacoteDto> updatePacote(@PathVariable(name = "id") Long id, @RequestBody PacoteDto pacoteDto) throws ResourceNotFoundException {
        log.info("Atualizando pacotes: {}", pacoteDto); // Registra um log de informação com os dados do pacote.
        return ResponseEntity.ok(pacoteService.editPacote(id, pacoteDto)); // Retorna o pacote atualizado em um ResponseEntity.
    }

    // Endpoint para deletar um pacote. DELETE /api/pacote/{id}
    @DeleteMapping("/{id}")
    @Operation(summary = "Remover Pacote", description = "Remove um Pacote no banco de Dados")
    public Map<String, Boolean> deletePacote(@PathVariable(name = "id") Long id) throws Exception {
        log.info("Deletando pacote: {}", id); // Registra um log de informação com o ID.
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", pacoteService.deletePacote(id)); // Chama o método do serviço para deletar o pacote.
        return response;
    }
}
