package com.qikserve.controller;

import com.qikserve.models.entities.ShoppingCart;
import com.qikserve.models.entities.Products;
import com.qikserve.services.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qikserve")
public class ProductController {
    @Autowired
    private ProductServiceImpl services;

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Products[]> listProduct(){
        ResponseEntity<Products[]> list = services.getProductList();
        return list;
    }


}
