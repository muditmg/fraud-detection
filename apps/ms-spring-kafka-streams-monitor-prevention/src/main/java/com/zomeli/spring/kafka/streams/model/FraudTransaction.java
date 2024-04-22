package com.zomeli.spring.kafka.streams.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FraudTransaction {

  private long id;
  private String accountNumber = null;
  private String lastMovementTimestamp = null;
  private double amountTotal = 0;
  private Integer sizeTransaction = 0;
  private List<TransactionAccount> transactionList = new ArrayList<>();

  @Override
  public String toString() {
    return " { " +
        " \"id\": " + id +
        ", \"accountNumber\": \"" + accountNumber + "\"" +
        ", \"lastMovementTimestamp\": \"" + lastMovementTimestamp + "\"" +
        ", \"transactionList\": " + transactionList +
        " }";
  }


}
