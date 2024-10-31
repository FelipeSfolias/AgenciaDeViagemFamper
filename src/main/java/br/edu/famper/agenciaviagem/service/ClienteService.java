package br.edu.famper.agenciaviagem.service;

import br.edu.famper.agenciaviagem.dto.ClienteDto;
import br.edu.famper.agenciaviagem.model.Cliente;
import br.edu.famper.agenciaviagem.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDto> getAllClientes(){
        return clienteRepository
                .findAll()
                .stream()
                .map(cliente -> ClienteDto
                        .builder()
                        .nome(cliente.getNome())
                        .cpf(cliente.getCpf())
                        .email(cliente.getEmail())
                        .telefone(cliente.getTelefone())
                        .endereco(cliente.getEndereco())
                        .build())
                .toList();
    }

    // buscar uma cliente
    public ClienteDto getClienteById(Long id){
        Cliente cli = clienteRepository.findById(id).orElseThrow();
        return new ClienteDto()
                .builder()
                .nome(cli.getNome())
                .cpf(cli.getCpf())
                .email(cli.getEmail())
                .telefone(cli.getTelefone())
                .endereco(cli.getEndereco())
                .build();
    }

    // inserir um cliente

    public Cliente saveCliente(ClienteDto clienteDto){
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDto.getNome());
        cliente.setCpf(clienteDto.getCpf());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setTelefone(clienteDto.getTelefone());
        cliente.setEndereco(clienteDto.getEndereco());
        return clienteRepository.save(cliente);
    }

    // editar um cliente
    public ClienteDto editCliente(Long id, ClienteDto clienteDto){
        Cliente cliente = clienteRepository.findById(id).orElseThrow();
        cliente.setNome(clienteDto.getNome());
        cliente.setCpf(clienteDto.getCpf());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setTelefone(clienteDto.getTelefone());
        cliente.setEndereco(clienteDto.getEndereco());
        Cliente clienteEdited = clienteRepository.save(cliente);
        return new ClienteDto()
                .builder()
                .nome(clienteEdited.getNome())
                .cpf(clienteEdited.getCpf())
                .email(clienteEdited.getEmail())
                .telefone(clienteEdited.getTelefone())
                .endereco(clienteEdited.getEndereco())
                .build();
    }

    // apagar um cliente
    public boolean deleteCliente(Long id){
        try{
            Cliente cliente = clienteRepository.findById(id).orElseThrow();
            clienteRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}