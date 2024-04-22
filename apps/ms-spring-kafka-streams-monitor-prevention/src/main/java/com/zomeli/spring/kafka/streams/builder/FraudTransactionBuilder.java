package com.zomeli.spring.kafka.streams.builder;

import com.zomeli.spring.kafka.streams.model.FraudTransaction;
import com.zomeli.spring.kafka.streams.model.MovementsAggregation;
import com.zomeli.spring.kafka.streams.model.TransactionAccount;
import org.springframework.stereotype.Component;

@Component
public class FraudTransactionBuilder {

  public FraudTransaction movementsAggregationToFraudCase(MovementsAggregation movementsAggregation) {

    double amount;

    var fraud = FraudTransaction.builder()
        .id(movementsAggregation.getId())
        .accountNumber(movementsAggregation.getAccountNumber())
        .lastMovementTimestamp(movementsAggregation.getLastMovementTimestamp());

    if (!movementsAggregation.getTransactionAccountList().isEmpty()) {
      amount = movementsAggregation.getTransactionAccountList().stream()
          .mapToDouble(TransactionAccount::getTotalAmount)
          .sum();

      fraud.transactionList(movementsAggregation.getTransactionAccountList())
          .sizeTransaction(movementsAggregation.getTransactionAccountList().size())
          .amountTotal(amount);
    }

    return fraud.build();
  }
}
