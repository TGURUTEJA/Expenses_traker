package com.expenses_traker.db_service.Repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.expenses_traker.db_service.model.Transaction;

public interface TransactionRepo extends ReactiveCrudRepository<Transaction, Long> {
}
