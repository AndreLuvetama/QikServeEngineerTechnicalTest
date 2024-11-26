package com.qikserve.models.entity;

import java.util.List;

public class Item {
    private Long id;
    private String name;
    private Double price;
    List<Promotion> promotions;
}
