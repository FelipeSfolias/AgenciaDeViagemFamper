package br.edu.famper.agenciaviagem.service;

import br.edu.famper.agenciaviagem.dto.PacoteDto;
import br.edu.famper.agenciaviagem.model.Pacote;
import br.edu.famper.agenciaviagem.repository.PacoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PacoteService {

    @Autowired
    private PacoteRepository pacoteRepository;

    public List<PacoteDto> getAllPacotes(){
        return pacoteRepository
                .findAll()
                .stream()
                .map(pacote -> PacoteDto
                        .builder()
                        .nome(pacote.getNome())
                        .descricao(pacote.getDescricao())
                        .preco(pacote.getPreco())
                        .dataFim(pacote.getDataFim())
                        .dataInicio(pacote.getDataInicio())
                        .destinoId(pacote.getId())
                        .build()
                )
                .toList();
    }

    // buscar uma cidade
    public PacoteDto getPacoteById(Long id){
        Pacote pac = pacoteRepository.findById(id).orElseThrow();
        new PacoteDto();
        return PacoteDto
                .builder()
                .nome(pac.getNome())
                .descricao(pac.getDescricao())
                .preco(pac.getPreco())
                .dataFim(pac.getDataFim())
                .dataInicio(pac.getDataInicio())
                .destinoId(pac.getId())
                .build();
    }

    // inserir uma cidade
    public Pacote savePacote(PacoteDto pacoteDto){
        Pacote pacote= new Pacote();
        pacote.setNome(pacoteDto.getNome());
        pacote.setDescricao(pacoteDto.getDescricao());
        pacote.setPreco(pacoteDto.getPreco());
        pacote.setDataFim(pacoteDto.getDataFim());
        pacote.setDataInicio(pacoteDto.getDataInicio());

        return pacoteRepository.save(pacote);
    }

    // editar uma cidade
    public PacoteDto editPacote(Long id, PacoteDto pacoteDto){
        Pacote pacote = pacoteRepository.findById(id).orElseThrow();
        pacote.setNome(pacoteDto.getNome());
        pacote.setDescricao(pacoteDto.getDescricao());
        pacote.setPreco(pacoteDto.getPreco());
        pacote.setDataFim(pacoteDto.getDataFim());
        pacote.setDataInicio(pacoteDto.getDataInicio());
        Pacote pacoteEdited = pacoteRepository.save(pacote);
        return new PacoteDto()
                .builder()
                .nome(pacoteEdited.getNome())
                .descricao(pacoteEdited.getDescricao())
                .preco(pacoteEdited.getPreco())
                .dataFim(pacoteEdited.getDataFim())
                .dataInicio(pacoteEdited.getDataInicio())
                .build();
    }

    // apagar uma cidade
    public boolean deletePacote(Long id){
        try{
            Pacote pacote = pacoteRepository.findById(id).orElseThrow();
            pacoteRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
