package br.edu.famper.agenciaviagem.repository;

import br.edu.famper.agenciaviagem.model.Destino;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface que define o repositório para a entidade Destino
// Extende JpaRepository para herdar métodos de acesso ao banco de dados
public interface DestinoRepository extends JpaRepository<Destino, Long> {
}
