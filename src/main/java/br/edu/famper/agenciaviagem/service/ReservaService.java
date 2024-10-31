package br.edu.famper.agenciaviagem.service;


import br.edu.famper.agenciaviagem.dto.ClienteDto;
import br.edu.famper.agenciaviagem.dto.ReservaDto;
import br.edu.famper.agenciaviagem.model.Cliente;
import br.edu.famper.agenciaviagem.model.Reserva;
import br.edu.famper.agenciaviagem.repository.ClienteRepository;
import br.edu.famper.agenciaviagem.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<ReservaDto> getAllReservas(){
        return reservaRepository
                .findAll()
                .stream()
                .map(reserva -> ReservaDto
                        .builder()
                        .clienteId(reserva.getCliente().getId())
                        .pacoteViagemId(reserva.getPacote().getId())
                        .dataReserva(reserva.getDataReserva())
                        .status(reserva.getStatus())
                        .build())
                .toList();
    }

    // buscar uma cliente
    public ReservaDto getReservaById(Long id){
        Reserva res = reservaRepository.findById(id).orElseThrow();
        return new ReservaDto()
                .builder()
                .clienteId(res.getCliente().getId())
                .pacoteViagemId(res.getPacote().getId())
                .dataReserva(res.getDataReserva())
                .status(res.getStatus())
                .build();
    }

    // inserir um cliente

    public Reserva saveReserva(ReservaDto reservaDto){
        Reserva reserva= new Reserva();
        reserva.setDataReserva(reservaDto.getDataReserva());
        reserva.setStatus(reservaDto.getStatus());
        reserva.setId(reservaDto.getClienteId());
        reserva.setId(reservaDto.getPacoteViagemId());
        return reservaRepository.save(reserva);

}

    // editar um cliente
    public ReservaDto editReserva(Long id, ReservaDto reservaDto){
        Reserva reserva= reservaRepository.findById(id).orElseThrow();
        reserva.setDataReserva(reservaDto.getDataReserva());
        reserva.setStatus(reservaDto.getStatus());
        reserva.setId(reservaDto.getClienteId());
        reserva.setId(reservaDto.getPacoteViagemId());
        Reserva reservaEdited = reservaRepository.save(reserva);
        return new ReservaDto()
                .builder()
                .status(reservaEdited.getStatus())
                .clienteId(reservaEdited.getCliente().getId())
                .pacoteViagemId(reservaEdited.getPacote().getId())
                .dataReserva(reservaEdited.getDataReserva())
                .status(reservaEdited.getStatus())
                .build();
    }

    // apagar um cliente
    public boolean deleteReserva(Long id){
        try{
            Reserva reserva = reservaRepository.findById(id).orElseThrow();
            reservaRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}