package com.zomeli.spring.kafka.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {


    private String number = null;

    private Product product = Product.builder().build();

    private String originationDate = null;

    private Customer customer = Customer.builder().build();

    private Double availableBalance = 0.0;

    private AccountSubtype accountSubtype = AccountSubtype.builder().build();

    @Override
    public String toString() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return " { " +
                " \"number\" : \"" + number + "\"" +
                ", \"product\" : " + product +
                ", \"originationDate\" : \"" + originationDate + "\"" +
                ", \"customer\" : " + customer +
                ", \"availableBalance\" : " + formatter.format(availableBalance) +
                ", \"accountSubtype\" : " + accountSubtype +
                " }";
    }
}
