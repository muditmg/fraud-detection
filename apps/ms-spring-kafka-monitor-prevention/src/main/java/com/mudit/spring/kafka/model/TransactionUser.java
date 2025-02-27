package com.zomeli.spring.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionUser {

    private Integer code = 0;

    private String user = null;

    @Override
    public String toString() {
        return " { " +
                "\"code\": " + code +
                ", \"user\": \"" + user + "\"" +
                " }";
    }
}
