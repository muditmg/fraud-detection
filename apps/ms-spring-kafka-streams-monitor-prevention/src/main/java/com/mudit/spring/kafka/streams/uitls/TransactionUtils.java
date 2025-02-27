package com.zomeli.spring.kafka.streams.uitls;


import com.zomeli.spring.kafka.streams.model.MovementsAggregation;
import com.zomeli.spring.kafka.streams.model.TransactionAccount;
import java.util.List;
import java.util.logging.Logger;

public class TransactionUtils {

  private static final Logger log = Logger.getLogger(String.valueOf(TransactionUtils.class));

  public static MovementsAggregation addMovement(String key, TransactionAccount transaction,
                                                 MovementsAggregation movementsAggregation) {

    movementsAggregation
        .setAccountNumber(transaction.getAccount().getNumber());
    movementsAggregation
        .setLastMovementTimestamp(transaction.getTransactionTime());
    movementsAggregation.addTransaction(transaction);

    log.info("[MovementsAggregation - addMovementKey "+key+" ] :  " + movementsAggregation);
    return movementsAggregation;
  }

  public static MovementsAggregation aggMerge(String key, MovementsAggregation fraudA,
                                              MovementsAggregation fraudB) {
//    log.info("[Merging aggregations - "+key+" ] : " +
//        "{ A (ID: " + fraudA.getId() + ", " + "Movs: " + fraudA.getTransactionAccountList().size() + " )}, " +
//        "B (ID: " + fraudB.getId() + ", Movs: " + fraudB.getTransactionAccountList().size() + " ) } ");
    return fraudB;
  }

  public static boolean isOnlineFraud(MovementsAggregation movementsAggregation) {


    if (movementsAggregation !=null && movementsAggregation.getTransactionAccountList().size() >= 2) {
      final List<TransactionAccount> movements = movementsAggregation.getTransactionAccountList();
      double totalAmount = movements.stream().mapToDouble(TransactionAccount::getTotalAmount).sum();

      return totalAmount > 200;
    }

    return false;
  }

  public static String generatedIdWithAccountIdAndProductCodeAndSubProductCode(
      TransactionAccount transaction) {

    var accountId = transaction.getAccount().getNumber();
    var productCode = String.valueOf(transaction.getAccount().getProduct().getCode());
    var subProductCode = String.valueOf(transaction.getAccount().getAccountSubtype().getCode());

    return accountId.concat(productCode).concat(subProductCode);
  }

}
