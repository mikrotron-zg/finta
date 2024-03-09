package hr.mikrotron.finta.transaction;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
  private final TransactionRepository transactionRepository;

  public TransactionService(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public List<Transaction> findAll() {
    return transactionRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
  }

  public List<Transaction> findUnpaired() {
    return findUnpaired(true);
  }

  public List<Transaction> findUnpaired(Boolean creditOnly) {
    if (creditOnly) {
      return transactionRepository.findAllUnpairedCredit();
    } else {
      return transactionRepository.findAllUnpaired();
    }
  }
}
