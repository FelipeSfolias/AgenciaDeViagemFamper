package br.edu.famper.agenciaviagem.service;

import br.edu.famper.agenciaviagem.dto.ReservaDto;
import br.edu.famper.agenciaviagem.model.Reserva;
import br.edu.famper.agenciaviagem.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service  // Indica que esta classe é um serviço do Spring, responsável pela lógica de negócio
@RequiredArgsConstructor
// Cria um construtor com os campos obrigatórios (não usado nesse caso específico, pois usa @Autowired)
@Slf4j  // Permite a criação de logs para essa classe
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository; // Repositório para interagir com a base de dados de Reserva

    // Método para buscar todos as reservas
    public List<ReservaDto> getAllReservas(){
        return reservaRepository
                .findAll()// Busca todos as reservas na base de dados
                .stream()
                .map(reserva -> ReservaDto// Mapeia cada Reserva para um ReservaDto
                        .builder()
                        .clienteId(reserva.getCliente().getId())
                        .pacoteViagemId(reserva.getPacote().getId())
                        .dataReserva(reserva.getDataReserva())
                        .status(reserva.getStatus())
                        .build())
                .toList();// Retorna uma lista de ReservaDto
    }

    // Método para buscar uma reserva específico pelo ID
    public ReservaDto getReservaById(Long id){
        Reserva res = reservaRepository.findById(id).orElseThrow(); // Busca resreva pelo ID, lança exceção se não encontrar
        return new ReservaDto()
                .builder()
                .clienteId(res.getCliente().getId())
                .pacoteViagemId(res.getPacote().getId())
                .dataReserva(res.getDataReserva())
                .status(res.getStatus())
                .build();// Converte a Reserva encontrado para uma ReservaDto e o retorna
    }

    // Método para inserir uma nova reserva

    public Reserva saveReserva(ReservaDto reservaDto){
        Reserva reserva = new Reserva();// Cria uma nova entidade Reserva
        reserva.setDataReserva(reservaDto.getDataReserva());// Define os atributos do Reserva a partir da ReservaDto
        reserva.setStatus(reservaDto.getStatus());
        reserva.setId(reservaDto.getClienteId());
        reserva.setId(reservaDto.getPacoteViagemId());
        return reservaRepository.save(reserva); // Salva a Reserva na base de dados e retorna a entidade salva

}

    // Método para editar uma reserva existente
    public ReservaDto editReserva(Long id, ReservaDto reservaDto){
        Reserva reserva = reservaRepository.findById(id).orElseThrow();// Busca a reserva pelo ID, lança exceção se não encontrar
        reserva.setDataReserva(reservaDto.getDataReserva());// Atualiza os atributos da reserva
        reserva.setStatus(reservaDto.getStatus());
        reserva.setId(reservaDto.getClienteId());
        reserva.setId(reservaDto.getPacoteViagemId());
        Reserva reservaEdited = reservaRepository.save(reserva);// Salva as mudanças no repositório
        return new ReservaDto()
                .builder()
                .status(reservaEdited.getStatus())
                .clienteId(reservaEdited.getCliente().getId())
                .pacoteViagemId(reservaEdited.getPacote().getId())
                .dataReserva(reservaEdited.getDataReserva())
                .status(reservaEdited.getStatus())
                .build();// Converte a Reserva atualizado para ReservaDto e o retorna
    }

    // Método para deletar uma reserva pelo ID
    public boolean deleteReserva(Long id){
        try{
            Reserva reserva = reservaRepository.findById(id).orElseThrow();
            reservaRepository.deleteById(id);// Remove o reserva pelo ID
            return true;// Retorna true se a exclusão foi bem-sucedida
        } catch (Exception e){
            return false;// Retorna false se houver uma exceção, indicando que a exclusão falhou
        }
    }

}

