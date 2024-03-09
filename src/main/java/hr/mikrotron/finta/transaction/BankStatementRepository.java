package hr.mikrotron.finta.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankStatementRepository extends JpaRepository<BankStatement, Integer> {
}
