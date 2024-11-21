package br.edu.famper.agenciaviagem.repository;

import br.edu.famper.agenciaviagem.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface que define o repositório para a entidade Pagamento
// Extende JpaRepository para herdar métodos de acesso ao banco de dados
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
