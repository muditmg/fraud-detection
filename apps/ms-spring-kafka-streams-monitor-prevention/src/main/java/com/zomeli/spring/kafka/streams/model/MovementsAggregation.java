package com.zomeli.spring.kafka.streams.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
@Data
public class MovementsAggregation {

  private long id;

  private String accountNumber = null;
  private String lastMovementTimestamp = null;
  private List<TransactionAccount> transactionAccountList;

  public MovementsAggregation() {
    this.id = Instant.now().getEpochSecond();
    this.transactionAccountList = new ArrayList<>();
  }

  public void addTransaction(TransactionAccount transactionAccount){
    this.transactionAccountList.add(transactionAccount);
  }

  @Override
  public String toString() {
    return " { " +
        " \"id\": " + id +
        ", \"accountNumber\": \"" + accountNumber + "\"" +
        ", \"lastMovementTimestamp\": \"" + lastMovementTimestamp + "\"" +
        ", \"transactionAccountList\": " + transactionAccountList +
        " }";
  }

}
