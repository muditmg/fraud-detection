package com.zomeli.spring.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Channel {

    private String code = null;

    @Override
    public String toString() {
        return " { " +
                "\"code\": \"" + code +  "\"" +
                " }";
    }
}
