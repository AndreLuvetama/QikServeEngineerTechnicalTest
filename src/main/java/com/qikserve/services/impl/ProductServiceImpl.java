package com.qikserve.services.impl;

import com.qikserve.models.entities.Products;
import com.qikserve.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductServiceImpl implements ProductServices {
    @Autowired
    private final RestTemplate template;
    private String apiUrl;

    public ProductServiceImpl(RestTemplate template){
        this.template = template;
    }

    @Override
    public ResponseEntity<Products[]> getProductList() {

        ResponseEntity<Products[]> products = template.getForEntity("http://localhost:8081/products",  Products[].class);
        if(products.getStatusCode() == HttpStatus.OK){
            return products;
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Products> getProductId(String id) {
        ResponseEntity<Products> list =
                template.getForEntity("http://localhost:8081/products/" + id, Products.class);
        if (list.getStatusCode() == HttpStatus.OK) {
            return list;
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
