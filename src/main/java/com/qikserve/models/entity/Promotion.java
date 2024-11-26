package com.qikserve.models.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qikserve.models.enuns.PromotionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Promotion {
    private String id;
    private PromotionType type;
    private int required_qty;
    private Double price;
}