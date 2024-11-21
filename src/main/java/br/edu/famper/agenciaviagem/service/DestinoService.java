package br.edu.famper.agenciaviagem.service;
import br.edu.famper.agenciaviagem.dto.DestinoDto;
import br.edu.famper.agenciaviagem.model.Destino;
import br.edu.famper.agenciaviagem.repository.DestinoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service  // Indica que esta classe é um serviço do Spring, responsável pela lógica de negócio
@RequiredArgsConstructor
// Cria um construtor com os campos obrigatórios (não usado nesse caso específico, pois usa @Autowired)
@Slf4j  // Permite a criação de logs para essa classe
public class DestinoService {

    @Autowired
    private DestinoRepository destinoRepository;// Repositório para interagir com a base de dados de Destino


    public List<DestinoDto> getAllDestinos(){
        return destinoRepository
                .findAll()// Busca todos os destinos na base de dados
                .stream()
                .map(destino -> DestinoDto// Mapeia cada Destino para DestinoDto
                        .builder()
                        .nomeDestino(destino.getNomeDestino())
                        .pais(destino.getPais())
                        .cidade(destino.getCidade())
                        .descricao(destino.getDescricao())
                        .build())
                .toList();// Retorna uma lista de DestinoDto
    }

    // Método para buscar um destino específico pelo ID
    public DestinoDto getDestinoById(Long id){
        Destino des = destinoRepository.findById(id).orElseThrow(); // Busca destino pelo ID, lança exceção se não encontrar
        new DestinoDto();
        return DestinoDto
                .builder()
                .nomeDestino(des.getNomeDestino())
                .pais(des.getPais())
                .cidade(des.getCidade())
                .descricao(des.getDescricao())
                .build();// Converte o Destino encontrado para um DestinoDto e o retorna
    }

    // Método para inserir um novo destino
    public Destino saveDestino(DestinoDto destinoDto){
        Destino destino = new Destino();// Cria uma nova entidade Destino
        destino.setNomeDestino(destinoDto.getNomeDestino());// Define os atributos do Destino a partir do DestinoDto
        destino.setPais(destinoDto.getPais());
        destino.setCidade(destinoDto.getCidade());
        destino.setDescricao(destinoDto.getDescricao());
        return destinoRepository.save(destino);// Salva o Destino na base de dados e retorna a entidade salva
    }

    // Método para editar um destino existente
    public DestinoDto editDestino(Long id, DestinoDto destinoDto){
        Destino destino = destinoRepository.findById(id).orElseThrow();// Busca o destino pelo ID, lança exceção se não encontrar
        destino.setNomeDestino(destinoDto.getNomeDestino());// Atualiza os atributos do destino
        destino.setPais(destinoDto.getPais());
        destino.setCidade(destinoDto.getCidade());
        destino.setDescricao(destinoDto.getDescricao());
        Destino destinoEdited = destinoRepository.save(destino);// Salva as mudanças no repositório
        return new DestinoDto()
                .builder()
                .nomeDestino(destinoEdited.getNomeDestino())
                .pais(destinoEdited.getPais())
                .cidade(destinoEdited.getCidade())
                .descricao(destinoEdited.getDescricao())
                .build();// Converte o Destino atualizado para DestinoDto e o retorna
    }

    // Método para deletar um destino pelo ID
    public boolean deleteDestino(Long id){
        try{
            Destino destino = destinoRepository.findById(id).orElseThrow();
            destinoRepository.deleteById(id);// Remove o cliente pelo ID
            return true;// Retorna true se a exclusão foi bem-sucedida
        } catch (Exception e){
            return false;// Retorna false se houver uma exceção, indicando que a exclusão falhou
        }
    }

}

