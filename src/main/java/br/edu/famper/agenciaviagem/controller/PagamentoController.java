package br.edu.famper.agenciaviagem.controller;

import br.edu.famper.agenciaviagem.dto.PacoteDto;
import br.edu.famper.agenciaviagem.dto.PagamentoDto;
import br.edu.famper.agenciaviagem.exception.ResourceNotFoundException;
import br.edu.famper.agenciaviagem.model.Cliente;
import br.edu.famper.agenciaviagem.model.Destino;
import br.edu.famper.agenciaviagem.model.Pacote;
import br.edu.famper.agenciaviagem.model.Pagamento;
import br.edu.famper.agenciaviagem.service.DestinoService;
import br.edu.famper.agenciaviagem.service.PacoteService;
import br.edu.famper.agenciaviagem.service.PagamentoService;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/pagamento")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Pagamento",
        description = "Operação com as opções de Pagamento")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca todos os Pagamento",
            description = "Busca todos os pagamentos do Banco de Dados e retorna " +
                    "um array em formato JSON"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "FOUND"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public List<PagamentoDto> getAllPagamentos() {
        log.info("Buscando todos os pagamentos");
        return pagamentoService.getAllPagamentos();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca um pagamento",
            description = "Busca um pagamento específico do Banco de Dados e retorna" +
                    "um objeto em formato JSON"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "FOUND"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ResponseEntity<PagamentoDto> getPagamentoById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando pagamento por id: {}", id);
        return ResponseEntity.ok().body(pagamentoService.getPagamentoById(id));
    }

    @PostMapping
    @Operation(summary = "Salva pagamento ",
            description = "Salva um pagamento no Banco de Dados"
    )
    public Pagamento createPagamento(@RequestBody PagamentoDto pagamentoDto) throws ResourceNotFoundException {
        log.info("Cadastro de pagamentos: {}", pagamentoDto);
        return pagamentoService.savePagamento(pagamentoDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza um Pagamento",
            description = "Atualiza um pagamento no Banco de Dados"
    )
    public ResponseEntity<PagamentoDto> updatePagamento(@PathVariable(name = "id") Long id, @RequestBody PagamentoDto pagamentoDto) throws ResourceNotFoundException {
        log.info("Atualizando pagamentos: {}", pagamentoDto);
        return ResponseEntity.ok(pagamentoService.editPagamento(id, pagamentoDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover Pagamento",
            description = "Remove um Pagamento no banco de Dados"
    )
    public Map<String, Boolean> deletePagamento(@PathVariable(name = "id") Long id) throws Exception {
        log.info("Deletando pagamento: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", pagamentoService.deletePagamento(id));
        return response;
    }


}