package com.qikserve;

import com.qikserve.models.entities.Products;
import com.qikserve.models.entities.Promotion;
import com.qikserve.models.entities.ShoppingCart;
import com.qikserve.models.enuns.PromotionType;
import com.qikserve.services.ProductServices;
import com.qikserve.services.ShoppingCartService;
import com.qikserve.services.impl.ProductServiceImpl;
import com.qikserve.services.impl.ShoppingCartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureWireMock(port = 8081)
public class QikServeEngineerTechnicalTestApplicationTests {
	@Autowired
	private ProductServiceImpl services;

	@Mock
	private ProductServices productServices;
	@Mock
	private ShoppingCartService shoppingCartService;

	@InjectMocks
	private ProductServiceImpl productServiceInpl;

	@InjectMocks
	private ShoppingCartServiceImpl shoppingCartImpl;


	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void newOrderWithEmptyList() {
		List<String> itemsId = Collections.emptyList();
		ShoppingCart shoppingCart = shoppingCartService.newOrder(itemsId);
		assertEquals(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_EVEN), shoppingCart.getTotalPrice());
		assertEquals(Collections.emptyList(), shoppingCart.getProducts());
	}
	@Test
	void newOrderWithDuplicateItems() {
		Products products = new Products();
		products.setId("Dwt5F7KAhi");
		products.setName("Amazing Pizza!");
		products.setPrice(1099.00);
		products.setPromotion(new ArrayList<>());
		ResponseEntity<Products> response = new ResponseEntity<>(products, HttpStatus.OK);
		when(productServices.getProductId("Dwt5F7KAhi")).thenReturn(response);

		List<String> itemsId = Arrays.asList("Dwt5F7KAhi", "Dwt5F7KAhi", "Dwt5F7KAhi");
		ShoppingCart shoppingCart = shoppingCartImpl.newOrder(itemsId);

		assertEquals(1, shoppingCart.getProducts().size());
		assertEquals(new BigDecimal(32.97).setScale(2, RoundingMode.HALF_EVEN), shoppingCart.getTotalPrice());
	}

	@Test
	void newOrderPromotion() {
		Promotion promotion = new Promotion("ibt3EEYczW", PromotionType.QTY_BASED_PRICE_OVERRIDE, 2,1799.0, null,null);
		List<Promotion> promotions = new ArrayList<>();
		promotions.add(promotion);

		Products products = new Products();
		products.setId("Dwt5F7KAhi");
		products.setName("Amazing Pizza!");
		products.setPrice(1099.00);
		products.setPromotion(promotions);

		ResponseEntity<Products> response = new ResponseEntity<>(products, HttpStatus.OK);
		when(productServiceInpl.getProductId("Dwt5F7KAhi")).thenReturn(response);

		List<String> itemsId = Arrays.asList("Dwt5F7KAhi", "Dwt5F7KAhi", "Dwt5F7KAhi");
		ShoppingCart shoppingCart = shoppingCartImpl.newOrder(itemsId);

		assertEquals(1, shoppingCart.getProducts().size());
		assertEquals(new BigDecimal(57.01).setScale(2, RoundingMode.HALF_EVEN), shoppingCart.getTotalPrice());
		assertEquals(new BigDecimal(54.02).setScale(2, RoundingMode.HALF_EVEN), shoppingCart.getPriceWithPromotions());
	}

}
