package br.edu.famper.agenciaviagem.repository;

import br.edu.famper.agenciaviagem.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface que define o repositório para a entidade Cliente
// Extende JpaRepository para herdar métodos de acesso ao banco de dados
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}