package com.qikserve.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qikserve.models.entities.Products;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShoppingRequest {
    @JsonProperty
    List<String> products;
}
