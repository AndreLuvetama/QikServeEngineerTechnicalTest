package com.qikserve.services;

import com.qikserve.models.entities.Products;
import org.springframework.http.ResponseEntity;


public interface ProductServices {
    ResponseEntity<Products[]> getProductList();
    ResponseEntity<Products> getProductId(String id);
}
