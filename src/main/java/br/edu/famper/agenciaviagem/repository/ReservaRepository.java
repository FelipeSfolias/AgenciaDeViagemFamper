package br.edu.famper.agenciaviagem.repository;

import br.edu.famper.agenciaviagem.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface que define o repositório para a entidade Reserva
// Extende JpaRepository para herdar métodos de acesso ao banco de dados
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
