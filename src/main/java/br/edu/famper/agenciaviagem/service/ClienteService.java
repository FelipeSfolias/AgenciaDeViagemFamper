package br.edu.famper.agenciaviagem.service;

import br.edu.famper.agenciaviagem.dto.ClienteDto;
import br.edu.famper.agenciaviagem.model.Cliente;
import br.edu.famper.agenciaviagem.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // Indica que esta classe é um serviço do Spring, responsável pela lógica de negócio
@RequiredArgsConstructor
// Cria um construtor com os campos obrigatórios (não usado nesse caso específico, pois usa @Autowired)
@Slf4j  // Permite a criação de logs para essa classe
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;  // Repositório para interagir com a base de dados de Cliente

    // Método para buscar todos os clientes
    public List<ClienteDto> getAllClientes(){
        return clienteRepository
                .findAll()  // Busca todos os clientes na base de dados
                .stream()
                .map(cliente -> ClienteDto  // Mapeia cada Cliente para um ClienteDto
                        .builder()
                        .nome(cliente.getNome())
                        .cpf(cliente.getCpf())
                        .email(cliente.getEmail())
                        .telefone(cliente.getTelefone())
                        .endereco(cliente.getEndereco())
                        .build())
                .toList();  // Retorna uma lista de ClienteDto
    }

    // Método para buscar um cliente específico pelo ID
    public ClienteDto getClienteById(Long id){
        Cliente cli = clienteRepository.findById(id).orElseThrow();  // Busca cliente pelo ID, lança exceção se não encontrar
        return new ClienteDto()
                .builder()
                .nome(cli.getNome())
                .cpf(cli.getCpf())
                .email(cli.getEmail())
                .telefone(cli.getTelefone())
                .endereco(cli.getEndereco())
                .build();  // Converte o Cliente encontrado para um ClienteDto e o retorna
    }

    // Método para inserir um novo cliente
    public Cliente saveCliente(ClienteDto clienteDto){
        Cliente cliente = new Cliente();  // Cria uma nova entidade Cliente
        cliente.setNome(clienteDto.getNome());  // Define os atributos do Cliente a partir do ClienteDto
        cliente.setCpf(clienteDto.getCpf());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setTelefone(clienteDto.getTelefone());
        cliente.setEndereco(clienteDto.getEndereco());
        return clienteRepository.save(cliente);  // Salva o Cliente na base de dados e retorna a entidade salva
    }

    // Método para editar um cliente existente
    public ClienteDto editCliente(Long id, ClienteDto clienteDto){
        Cliente cliente = clienteRepository.findById(id).orElseThrow();  // Busca o cliente pelo ID, lança exceção se não encontrar
        cliente.setNome(clienteDto.getNome());  // Atualiza os atributos do cliente
        cliente.setCpf(clienteDto.getCpf());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setTelefone(clienteDto.getTelefone());
        cliente.setEndereco(clienteDto.getEndereco());
        Cliente clienteEdited = clienteRepository.save(cliente);  // Salva as mudanças no repositório
        return new ClienteDto()
                .builder()
                .nome(clienteEdited.getNome())
                .cpf(clienteEdited.getCpf())
                .email(clienteEdited.getEmail())
                .telefone(clienteEdited.getTelefone())
                .endereco(clienteEdited.getEndereco())
                .build();  // Converte o Cliente atualizado para ClienteDto e o retorna
    }

    // Método para deletar um cliente pelo ID
    public boolean deleteCliente(Long id){
        try{
            Cliente cliente = clienteRepository.findById(id).orElseThrow();  // Verifica se o cliente existe
            clienteRepository.deleteById(id);  // Remove o cliente pelo ID
            return true;  // Retorna true se a exclusão foi bem-sucedida
        } catch (Exception e){
            return false;  // Retorna false se houver uma exceção, indicando que a exclusão falhou
        }
    }

}
