package com.zomeli.spring.kafka.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private Integer code = 0;

    @Override
    public String toString() {
        return "{ " +
                "\"code\" :" + code +
                " }";
    }
}
