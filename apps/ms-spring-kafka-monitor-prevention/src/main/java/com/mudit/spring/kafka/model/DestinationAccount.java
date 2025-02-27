package com.zomeli.spring.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DestinationAccount {

    private String number = null;

    private Customer customer = Customer.builder().build();

    @Override
    public String toString() {
        return " { " +
                "\"number\": \"" + number + "\"" +
                ", \"customer\": " + customer +
                " }";
    }
}
