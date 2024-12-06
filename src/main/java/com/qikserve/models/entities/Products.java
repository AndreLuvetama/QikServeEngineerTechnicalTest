package com.qikserve.models.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor

@Setter
@Getter
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Products {
    private String id;
    private String name;
    private Double price;
    private Integer quantity;
    private List<Promotion> promotion;
    public Products() {
    }


}
