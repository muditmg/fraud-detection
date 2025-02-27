package com.zomeli.spring.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {


    private Integer code = 0;

    private String names = null;

    private Category category = Category.builder().build();

    @Override
    public String toString() {
        return " { " +
                " \"code\" : " + code +
                ", \"names\" : \"" + names + "\"" +
                ", \"category\" : " + category +
                " }";
    }
}
