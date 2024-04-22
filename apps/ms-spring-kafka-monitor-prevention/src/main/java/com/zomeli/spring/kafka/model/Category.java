package com.zomeli.spring.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    private String description = null;

    @Override
    public String toString() {
        return " { " +
                "\"description\": \"" + description + "\"" +
                " }";
    }
}
