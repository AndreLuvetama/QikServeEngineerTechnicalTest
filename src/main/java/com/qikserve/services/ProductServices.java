package com.qikserve.services;

import com.qikserve.models.entity.Products;

import java.util.List;

public interface ProductServices {
    List<Products> getProductList();
}
