package com.example.product;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.product.controller.ProductController;
import com.example.product.dto.AddProduct;
import com.example.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@ContextConfiguration(classes = { ProductController.class })
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@InjectMocks
	private ProductController controller;

	ProductModel model;

	@Before
	public void initDeviceTier() {
		model.setAvailableForSale(true);
		model.setProductCost(6000L);
		model.setProductName("abc");
	}

	@Test
	public void getProduct() throws Exception {
		mockMvc.perform(get("/product/fetch-product").param("pageNo", "1").param("pageSize", "2"))
				.andExpect(status().is(200)).andReturn();
	}

	@Test
	public void addProduct() throws Exception {
		mockMvc.perform(get("/product/add-product")).andExpect(status().is(200)).andReturn();
	}

	@Test
	public void addProductHappyPathTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		AddProduct addProduct = new AddProduct();
		String jsonString = mapper.writeValueAsString(addProduct);
		when(productService.addProduct(Mockito.any())).thenReturn(null);
		mockMvc.perform(post("/product/add-product").contentType(MediaType.APPLICATION_JSON)
				.content(jsonString)).andExpect(status().is(200)).andReturn();
	}
	
	@Test
	public void UpdateProductHappyPathTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		AddProduct addProduct = new AddProduct();
		String jsonString = mapper.writeValueAsString(addProduct);
		when(productService.updateProduct(Mockito.anyLong(), addProduct)).thenReturn(null);
		mockMvc.perform(post("/product/update-product").contentType(MediaType.APPLICATION_JSON)
				.content(jsonString)).andExpect(status().is(200)).andReturn();
	}

}
