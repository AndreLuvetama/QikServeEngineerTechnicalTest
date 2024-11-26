package com.qikserve.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {
    private BigDecimal totalPrice;
    private BigDecimal priceWithPromotions;
    private List<Products> products;

}
