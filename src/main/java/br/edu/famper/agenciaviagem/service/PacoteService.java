package br.edu.famper.agenciaviagem.service;

import br.edu.famper.agenciaviagem.dto.PacoteDto;
import br.edu.famper.agenciaviagem.model.Pacote;
import br.edu.famper.agenciaviagem.repository.PacoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service  // Indica que esta classe é um serviço do Spring, responsável pela lógica de negócio
@RequiredArgsConstructor
// Cria um construtor com os campos obrigatórios (não usado nesse caso específico, pois usa @Autowired)
@Slf4j  // Permite a criação de logs para essa classe
public class PacoteService {

    @Autowired
    private PacoteRepository pacoteRepository;// Repositório para interagir com a base de dados de Pacote

    // Método para buscar todos os pacotes
    public List<PacoteDto> getAllPacotes(){
        return pacoteRepository
                .findAll()// Busca todos os pacotes na base de dados
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
                .toList();// Retorna uma lista de PacoteDto
    }

    // Método para buscar um pacote específico pelo ID
    public PacoteDto getPacoteById(Long id){
        Pacote pac = pacoteRepository.findById(id).orElseThrow();// Busca cliente pelo ID, lança exceção se não encontrar
        new PacoteDto();
        return PacoteDto
                .builder()
                .nome(pac.getNome())
                .descricao(pac.getDescricao())
                .preco(pac.getPreco())
                .dataFim(pac.getDataFim())
                .dataInicio(pac.getDataInicio())
                .destinoId(pac.getId())
                .build();// Converte o pacote encontrado para um PacoteDto e o retorna
    }

    // Método para inserir um novo pacote
    public Pacote savePacote(PacoteDto pacoteDto){
        Pacote pacote = new Pacote();// Cria uma nova entidade Pacote
        pacote.setNome(pacoteDto.getNome());// Define os atributos do Pacote a partir do pacoteDto
        pacote.setDescricao(pacoteDto.getDescricao());
        pacote.setPreco(pacoteDto.getPreco());
        pacote.setDataFim(pacoteDto.getDataFim());
        pacote.setDataInicio(pacoteDto.getDataInicio());

        return pacoteRepository.save(pacote); // Salva o Pacote na base de dados e retorna a entidade salva
    }

    // Método para editar um pacote existente
    public PacoteDto editPacote(Long id, PacoteDto pacoteDto){
        Pacote pacote = pacoteRepository.findById(id).orElseThrow();// Busca o cliente pelo ID, lança exceção se não encontrar
        pacote.setNome(pacoteDto.getNome());// Atualiza os atributos do pacote
        pacote.setDescricao(pacoteDto.getDescricao());
        pacote.setPreco(pacoteDto.getPreco());
        pacote.setDataFim(pacoteDto.getDataFim());
        pacote.setDataInicio(pacoteDto.getDataInicio());
        Pacote pacoteEdited = pacoteRepository.save(pacote);// Salva as mudanças no repositório
        return new PacoteDto()
                .builder()
                .nome(pacoteEdited.getNome())
                .descricao(pacoteEdited.getDescricao())
                .preco(pacoteEdited.getPreco())
                .dataFim(pacoteEdited.getDataFim())
                .dataInicio(pacoteEdited.getDataInicio())
                .build();// Converte o Pacote atualizado para PacoteDto e o retorna
    }

    // Método para deletar um Pacot pelo ID
    public boolean deletePacote(Long id){
        try{
            Pacote pacote = pacoteRepository.findById(id).orElseThrow();// Verifica se o cliente existe
            pacoteRepository.deleteById(id);// Remove o cliente pelo ID
            return true;// Retorna true se a exclusão foi bem-sucedida
        } catch (Exception e){
            return false; //Retorna false se houver uma exceção, indicando que a exclusão falhou
        }
    }

}

