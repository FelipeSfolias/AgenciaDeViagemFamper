package br.edu.famper.agenciaviagem.repository;

import br.edu.famper.agenciaviagem.model.Pacote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacoteRepository extends JpaRepository<Pacote, Long> {
}
