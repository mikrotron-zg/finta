package hr.mikrotron.finta.transaction;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankStatementService {
  private final BankStatementRepository bankStatementRepository;
  private final TransactionRepository transactionRepository;

  public BankStatementService(BankStatementRepository bankStatementRepository,
                              TransactionRepository transactionRepository) {
    this.bankStatementRepository = bankStatementRepository;
    this.transactionRepository = transactionRepository;
  }

  public BankStatement save(BankStatement bankStatement) {
    BankStatement returnValue = bankStatementRepository.save(bankStatement);
    transactionRepository.saveAll(bankStatement.getTransactions());
    return returnValue;
  }

  public List<BankStatement> findAll() {
    return bankStatementRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
  }

  public BankStatement findByFileName(String fileName) {
    return bankStatementRepository.findFirstByFileName(fileName);
  }
}
