package br.edu.famper.agenciaviagem.service;
import br.edu.famper.agenciaviagem.dto.DestinoDto;
import br.edu.famper.agenciaviagem.model.Destino;
import br.edu.famper.agenciaviagem.repository.DestinoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class DestinoService {

    @Autowired
    private DestinoRepository destinoRepository;

    public List<DestinoDto> getAllDestinos(){
        return destinoRepository
                .findAll()
                .stream()
                .map(destino -> DestinoDto
                        .builder()
                        .nomeDestino(destino.getNomeDestino())
                        .pais(destino.getPais())
                        .cidade(destino.getCidade())
                        .descricao(destino.getDescricao())
                        .build()
                )
                .toList();
    }

    // buscar uma cidade
    public DestinoDto getDestinoById(Long id){
        Destino des = destinoRepository.findById(id).orElseThrow();
        new DestinoDto();
        return DestinoDto
                .builder()
                .nomeDestino(des.getNomeDestino())
                .pais(des.getPais())
                .cidade(des.getCidade())
                .descricao(des.getDescricao())
                .build();
    }

    // inserir uma cidade
    public Destino saveDestino(DestinoDto destinoDto){
        Destino destino = new Destino();
        destino.setNomeDestino(destinoDto.getNomeDestino());
        destino.setPais(destinoDto.getPais());
        destino.setCidade(destinoDto.getCidade());
        destino.setDescricao(destinoDto.getDescricao());
        return destinoRepository.save(destino);
    }

    // editar uma cidade
    public DestinoDto editDestino(Long id, DestinoDto destinoDto){
        Destino destino = destinoRepository.findById(id).orElseThrow();
        destino.setNomeDestino(destinoDto.getNomeDestino());
        destino.setPais(destinoDto.getPais());
        destino.setCidade(destinoDto.getCidade());
        destino.setDescricao(destinoDto.getDescricao());
        Destino destinoEdited = destinoRepository.save(destino);
        return new DestinoDto()
                .builder()
                .nomeDestino(destinoEdited.getNomeDestino())
                .pais(destinoEdited.getPais())
                .cidade(destinoEdited.getCidade())
                .descricao(destinoEdited.getDescricao())
                .build();
    }

    // apagar uma cidade
    public boolean deleteDestino(Long id){
        try{
            Destino destino = destinoRepository.findById(id).orElseThrow();
            destinoRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}

