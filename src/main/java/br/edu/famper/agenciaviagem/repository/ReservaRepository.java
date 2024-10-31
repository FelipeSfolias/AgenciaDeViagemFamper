package br.edu.famper.agenciaviagem.repository;

import br.edu.famper.agenciaviagem.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
