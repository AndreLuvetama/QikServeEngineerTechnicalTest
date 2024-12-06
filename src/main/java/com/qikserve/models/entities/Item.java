package com.qikserve.models.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {
    private Long id;
    private String name;
    private Double price;
    List<Promotion> promotions;
    public Item() {

    }
}
