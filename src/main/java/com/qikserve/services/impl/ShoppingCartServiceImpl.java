package com.qikserve.services.impl;

import com.qikserve.models.entities.Promotion;
import com.qikserve.models.entities.ShoppingCart;
import com.qikserve.models.entities.Products;
import com.qikserve.models.enuns.PromotionType;
import com.qikserve.services.ProductServices;
import com.qikserve.services.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ProductServices servicesProduct;
    @Override
    public ShoppingCart newOrder(List<String> productId) {
        List<String>  value = new ArrayList<>(new HashSet<>(productId));

        List<Products> products = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart();
        Double totalPrice = 0.0;
        List<Products> listProducts = new ArrayList<>();

        for(String id: value){
            int quantity = Collections.frequency(productId, id);
            Products prod = this.servicesProduct.getProductId(id).getBody();
            prod.setQuantity(quantity);
            products.add(prod);
            totalPrice += prod.getPrice() * quantity;
        }
        shoppingCart.setTotalPrice(new BigDecimal(totalPrice));
        shoppingCart.setProducts(products);

        return promotions(shoppingCart);
    }

    private ShoppingCart promotions(ShoppingCart shoppingCart){
        Double discount = 0.0;
        for(Products products: shoppingCart.getProducts()){
            if(!products.getPromotion().isEmpty()){
                Promotion promotion = products.getPromotion().get(0);
                if(PromotionType.FLAT_PERCENT.equals(promotion.getType())){
                    if(products.getQuantity() >= promotion.getRequired_qty()){
                        int timesPromotionsApplied = products.getQuantity()/promotion.getRequired_qty();
                        int freeProducts = timesPromotionsApplied * promotion.getFreeQty();
                        discount += products.getPrice() * freeProducts;
                    }
                }
                if(PromotionType.QTY_BASED_PRICE_OVERRIDE.equals(promotion.getType())){
                    if(products.getQuantity() >= promotion.getRequired_qty()){
                        int timesPromotionsApplied = products.getQuantity()/promotion.getRequired_qty();
                        Double promotionPrice = timesPromotionsApplied * promotion.getPrice();
                        Double productPrice = products.getPrice() * products.getQuantity();
                        Double newProductPrice = productPrice - ((timesPromotionsApplied*promotion.getRequired_qty()) * products.getPrice()) + promotionPrice;
                        Double productDiscount = productPrice - newProductPrice;
                        discount += productDiscount;
                    }
                }
                if(PromotionType.FLAT_PERCENT.equals(promotion.getType())){
                    Double productPrice = products.getPrice() * products.getQuantity();
                    discount += productPrice * (double) promotion.getAmount()/100;
                }
            }
        }
        Double priceWithPromotion = shoppingCart.getTotalPrice().doubleValue() - discount;
        shoppingCart.setPriceWithPromotions(new BigDecimal(priceWithPromotion));
        return formatPrices(shoppingCart);
    }

    private ShoppingCart formatPrices(ShoppingCart shoppingCart){
        if(shoppingCart.getTotalPrice()!= null){
            shoppingCart.setTotalPrice(shoppingCart.getTotalPrice().divide(new BigDecimal(100)));
            shoppingCart.setTotalPrice(shoppingCart.getTotalPrice().setScale(2, RoundingMode.HALF_EVEN));
        }
        if(shoppingCart.getPriceWithPromotions() != null){
            shoppingCart.setPriceWithPromotions(shoppingCart.getPriceWithPromotions().divide(new BigDecimal(100)));
            shoppingCart.setPriceWithPromotions(shoppingCart.getPriceWithPromotions().setScale(2, RoundingMode.HALF_EVEN));
        }

        for (Products products: shoppingCart.getProducts()){
            if(products.getPrice()!= null){
                products.setPrice(products.getPrice()/100);
            }

            for(Promotion promotion: products.getPromotion()){
                if(promotion.getPrice() != null){
                    promotion.setPrice(promotion.getPrice()/100);
                }
            }
        }
        return shoppingCart;
    }

}
