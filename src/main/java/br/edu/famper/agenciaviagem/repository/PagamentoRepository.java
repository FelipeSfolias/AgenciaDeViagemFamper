package br.edu.famper.agenciaviagem.repository;

import br.edu.famper.agenciaviagem.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
