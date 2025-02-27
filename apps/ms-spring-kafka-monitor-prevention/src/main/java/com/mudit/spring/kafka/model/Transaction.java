package com.zomeli.spring.kafka.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private String transactionType = null;

    private Integer code = 0;

    private Integer correctionSequential = 0;

    private String status = null;

    private String postingDate = null;

    private Integer originalTransaction = 0;

    private Subsidiary subsidiary = Subsidiary.builder().build();

    private Branch branch = Branch.builder().build();

    private TransactionUser transactionUser = TransactionUser.builder().build();

    private String terminal = null;

    private String operation = null;

    private Double totalAmount = 0.0;

    private Currency currency = Currency.builder().build();

    private Nature nature = Nature.builder().build();

    private Double accountingBalance = 0.0;

    private Double availableBalance = 0.0;

    private Account account = Account.builder().build();

    private DestinationAccount destinationAccount = DestinationAccount.builder().build();

    private Integer checkNumber = 0;

    private String concept = null;

    private String transactionTime = null;

    private Channel channel = Channel.builder().build();

    private Integer transaction = null;

    private String sourceFile = null;

    private String voucherId = null;

    @Override
    public String toString() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return " { " +
            " \"transactionType\" : \"" + transactionType + "\"" +
            ", \"code\" : " + code +
            ", \"correctionSequential\" : " + correctionSequential +
            ", \"status\": \"" + status + "\"" +
            ", \"postingDate\": \"" + postingDate + "\"" +
            ", \"originalTransaction\" : " + originalTransaction +
            ", \"subsidiary\" : " + subsidiary +
            ", \"branch\" : " + branch +
            ", \"transactionUser\" : " + transactionUser +
            ", \"terminal\": \"" + terminal + "\"" +
            ", \"operation\": \"" + operation + "\"" +
            ", \"totalAmount\" : " + formatter.format(totalAmount) +
            ", \"currency\" : " + currency +
            ", \"nature\" : " + nature +
            ", \"accountingBalance\" : " + formatter.format(accountingBalance) +
            ", \"availableBalance\" : " + formatter.format(availableBalance) +
            ", \"account\" : " + account +
            ", \"destinationAccount\" : " + destinationAccount +
            ", \"checkNumber\" : " + checkNumber +
            ", \"concept\": \"" + concept + "\"" +
            ", \"transactionTime\": \"" + transactionTime + "\"" +
            ", \"channel\" : " + channel +
            ", \"transaction\" : " + transaction +
            ", \"sourceFile\": \"" + sourceFile + "\"" +
            ", \"voucherId\" : \"" + voucherId + "\"" +
            " }";
    }

}
