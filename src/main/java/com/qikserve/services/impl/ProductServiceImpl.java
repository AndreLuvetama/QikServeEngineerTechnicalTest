package com.qikserve.services.impl;

import com.qikserve.models.entity.Products;
import com.qikserve.services.ProductServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductServices {
    @Override
    public List<Products> getProductList() {
        return null;
    }
}
