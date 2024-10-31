package br.edu.famper.agenciaviagem.repository;

import br.edu.famper.agenciaviagem.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
