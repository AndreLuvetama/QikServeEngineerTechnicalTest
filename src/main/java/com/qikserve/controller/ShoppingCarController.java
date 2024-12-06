package com.qikserve.controller;

import com.qikserve.dto.ShoppingRequest;
import com.qikserve.models.entities.ShoppingCart;
import com.qikserve.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("shopping")
public class ShoppingCarController {
    @Autowired
    private ShoppingCartService service;

    @PostMapping("/productsId")
    public ResponseEntity<ShoppingCart> addOrder(@RequestBody ShoppingRequest shoppingCart){
        ShoppingCart cart = this.service.newOrder(shoppingCart.getProducts());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
