package br.edu.famper.agenciaviagem.repository;

import br.edu.famper.agenciaviagem.model.Pacote;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface que define o repositório para a entidade Pacote
// Extende JpaRepository para herdar métodos de acesso ao banco de dados
public interface PacoteRepository extends JpaRepository<Pacote, Long> {
}
