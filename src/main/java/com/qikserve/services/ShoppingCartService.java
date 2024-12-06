package com.qikserve.services;

import com.qikserve.models.entities.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShoppingCartService {
    public ShoppingCart newOrder(List<String> productId);
}
