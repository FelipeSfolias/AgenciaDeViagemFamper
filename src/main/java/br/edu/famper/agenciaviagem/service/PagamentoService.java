package br.edu.famper.agenciaviagem.service;
import br.edu.famper.agenciaviagem.dto.PagamentoDto;
import br.edu.famper.agenciaviagem.model.Pagamento;
import br.edu.famper.agenciaviagem.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<PagamentoDto> getAllPagamentos() {
        return pagamentoRepository
                .findAll()
                .stream()
                .map(pagamento -> PagamentoDto
                        .builder()
                        .metodoPagamento(pagamento.getMetodoPagamento())
                        .dataPagamento(pagamento.getDataPagamento())
                        .valor(pagamento.getValor())
                        .build())
                .toList();
    }

    // buscar uma cidade
    public PagamentoDto getPagamentoById(Long id) {
        Pagamento pag = pagamentoRepository.findById(id).orElseThrow();
        new PagamentoDto();
        return PagamentoDto
                .builder()
                .metodoPagamento(pag.getMetodoPagamento())
                .dataPagamento(pag.getDataPagamento())
                .valor(pag.getValor())
                .reservaId(pag.getReserva().getId())
                .build();
    }

    // inserir uma cidade
    public Pagamento savePagamento(PagamentoDto pagamentoDto) {
        Pagamento pagamento = new Pagamento();
        pagamento.setMetodoPagamento(pagamentoDto.getMetodoPagamento());
        pagamento.setDataPagamento(pagamentoDto.getDataPagamento());
        pagamento.setValor(pagamentoDto.getValor());
        pagamento.setId(pagamentoDto.getReservaId());
        return pagamentoRepository.save(pagamento);
    }


    // editar uma cidade
    public PagamentoDto editPagamento(Long id, PagamentoDto pagamentoDto) {
        Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow();
        pagamento.setMetodoPagamento(pagamentoDto.getMetodoPagamento());
        pagamento.setDataPagamento(pagamentoDto.getDataPagamento());
        pagamento.setValor(pagamentoDto.getValor());
        pagamento.setId(id);
        Pagamento pagamentoEdited = pagamentoRepository.save(pagamento);
        return new PagamentoDto()
                .builder()
                .metodoPagamento(pagamentoEdited.getMetodoPagamento())
                .dataPagamento(pagamentoEdited.getDataPagamento())
                .valor(pagamentoEdited.getValor())
                .reservaId(pagamentoEdited.getId())
                .build();
    }

    // apagar uma cidade
    public boolean deletePagamento(Long id) {
        try {
            Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow();
            pagamentoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}