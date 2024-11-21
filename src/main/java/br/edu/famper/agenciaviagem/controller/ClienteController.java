package br.edu.famper.agenciaviagem.controller;

import br.edu.famper.agenciaviagem.dto.ClienteDto;
import br.edu.famper.agenciaviagem.exception.ResourceNotFoundException;
import br.edu.famper.agenciaviagem.model.Cliente;
import br.edu.famper.agenciaviagem.service.ClienteService;
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
// Indica que esta classe é um controlador REST, onde os métodos retornam dados no formato JSON por padrão
@RequestMapping("/api/cliente")  // Define a URL base para os endpoints deste controlador
@RequiredArgsConstructor  // Gera um construtor com todos os atributos obrigatórios (final) para injeção de dependência
@Slf4j  // Habilita o uso do logger para registrar informações
@Tag(name = "Cliente", description = "Clientes")
// Documentação Swagger para o grupo de endpoints relacionados a Cliente
public class ClienteController {

    private final ClienteService clienteService;  // Injeta o serviço ClienteService para acessar operações de negócio

    @GetMapping
    @ResponseStatus(HttpStatus.OK)  // Define o status HTTP 200 para este endpoint
    @Operation(summary = "Get todos clientes", description = "Pega todos os clientes no BD no formato JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "FOUND"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public List<ClienteDto> getAllClientes() {
        log.info("Buscando todos os Clientes");
        return clienteService.getAllClientes();  // Retorna uma lista de todos os clientes
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca um cliente pelo ID", description = "Busca um cliente pelo ID e retorna um objeto em JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "FOUND"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando cliente por id: {}", id);
        return ResponseEntity.ok().body(clienteService.getClienteById(id));  // Retorna o cliente pelo ID, ou lança uma exceção se não encontrado
    }

    @PostMapping
    @Operation(summary = "Salva uma Cliente", description = "Salva uma cliente no Banco de Dados")
    public Cliente createCidade(@RequestBody ClienteDto clienteDto) throws ResourceNotFoundException {
        log.info("Cadastro cliente: {}", clienteDto);
        return clienteService.saveCliente(clienteDto);  // Cria um novo cliente com os dados do clienteDto
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza um cliente", description = "Atualiza o cadastro de cliente no Banco de Dados")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable(name = "id") Long id, @RequestBody ClienteDto clienteDto) throws ResourceNotFoundException {
        log.info("Atualizando cliente: {}", clienteDto);
        return ResponseEntity.ok(clienteService.editCliente(id, clienteDto));  // Atualiza o cliente pelo ID com os dados do clienteDto
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove Cliente", description = "Remove um Cliente no Banco de Dados")
    public Map<String, Boolean> deleteCidade(@PathVariable(name = "id") Long id) throws Exception {
        log.info("Deletando cliente: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", clienteService.deleteCliente(id));  // Remove o cliente pelo ID e retorna o status da operação
        return response;
    }
}