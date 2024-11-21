package br.edu.famper.agenciaviagem.service;
import br.edu.famper.agenciaviagem.dto.PagamentoDto;
import br.edu.famper.agenciaviagem.model.Pagamento;
import br.edu.famper.agenciaviagem.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // Indica que esta classe é um serviço do Spring, responsável pela lógica de negócio
@RequiredArgsConstructor
// Cria um construtor com os campos obrigatórios (não usado nesse caso específico, pois usa @Autowired)
@Slf4j  // Permite a criação de logs para essa classe
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;// Repositório para interagir com a base de dados de Pagamento

    // Método para buscar todos os pagamentos
    public List<PagamentoDto> getAllPagamentos() {
        return pagamentoRepository
                .findAll()// Busca todos os pagamentos na base de dados
                .stream()
                .map(pagamento -> PagamentoDto// Mapeia cada Pagamento para um PagamentoDto
                        .builder()
                        .metodoPagamento(pagamento.getMetodoPagamento())
                        .dataPagamento(pagamento.getDataPagamento())
                        .valor(pagamento.getValor())
                        .build())
                .toList();// Retorna uma lista de PagamentoDto
    }

    // Método para buscar um pagamento específico pelo ID
    public PagamentoDto getPagamentoById(Long id) {
        Pagamento pag = pagamentoRepository.findById(id).orElseThrow();
        new PagamentoDto();
        return PagamentoDto
                .builder()
                .metodoPagamento(pag.getMetodoPagamento())
                .dataPagamento(pag.getDataPagamento())
                .valor(pag.getValor())
                .reservaId(pag.getReserva().getId())
                .build(); // Converte o Pagamento encontrado para um PagamentoDto e o retorna
    }

    // Método para inserir um novo pagemerto
    public Pagamento savePagamento(PagamentoDto pagamentoDto) {
        Pagamento pagamento = new Pagamento(); // Cria uma nova entidade Pagamento
        pagamento.setMetodoPagamento(pagamentoDto.getMetodoPagamento());// Define os atributos do Pagamento a partir do PagamentoDto
        pagamento.setDataPagamento(pagamentoDto.getDataPagamento());
        pagamento.setValor(pagamentoDto.getValor());
        pagamento.setId(pagamentoDto.getReservaId());
        return pagamentoRepository.save(pagamento);// Salva o Pagamento na base de dados e retorna a entidade salva
    }


    // Método para editar um pagamento existente
    public PagamentoDto editPagamento(Long id, PagamentoDto pagamentoDto) {
        Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow();// Busca o pagamento pelo ID, lança exceção se não encontrar
        pagamento.setMetodoPagamento(pagamentoDto.getMetodoPagamento()); // Atualiza os atributos do pagamento
        pagamento.setDataPagamento(pagamentoDto.getDataPagamento());
        pagamento.setValor(pagamentoDto.getValor());
        pagamento.setId(id);
        Pagamento pagamentoEdited = pagamentoRepository.save(pagamento);// Salva as mudanças no repositório
        return new PagamentoDto()
                .builder()
                .metodoPagamento(pagamentoEdited.getMetodoPagamento())
                .dataPagamento(pagamentoEdited.getDataPagamento())
                .valor(pagamentoEdited.getValor())
                .reservaId(pagamentoEdited.getId())
                .build();// Converte o Pagamento atualizado para PagamentoDto e o retorna
    }

    // Método para deletar um cliente pelo ID
    public boolean deletePagamento(Long id) {
        try {
            Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow();// Verifica se o cliente existe
            pagamentoRepository.deleteById(id);// Remove o cliente pelo ID
            return true;// Retorna true se a exclusão foi bem-sucedida
        } catch (Exception e) {
            return false;// Retorna false se houver uma exceção, indicando que a exclusão falhou
        }
    }
}
