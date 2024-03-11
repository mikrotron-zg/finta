package hr.mikrotron.finta.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
  @Query("select t from Transaction t where t.paired = false")
  List<Transaction> findAllUnpaired();

  @Query("select t from Transaction t where t.paired = false and credit > 0")
  List<Transaction> findAllUnpairedCredit();
}
