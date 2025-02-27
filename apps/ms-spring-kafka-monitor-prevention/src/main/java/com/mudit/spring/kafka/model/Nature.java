package com.zomeli.spring.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Nature {


    private String code = null;

    private String description = null;

    @Override
    public String toString() {
        return " { " +
                "\"code\": \"" + code + "\"" +
                ", \"description\": \"" + description + "\"" +
                " }";
    }
}
