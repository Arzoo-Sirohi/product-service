package com.example.product.service;

import org.springframework.http.ResponseEntity;

import com.example.product.dto.AddProduct;
import com.example.product.dto.DeleteProduct;
import com.example.product.dto.Response;

public interface ProductService {

	ResponseEntity<Response> addProduct(AddProduct addProduct);

	ResponseEntity<Response> deleteProduct(DeleteProduct deleteProduct);

	ResponseEntity<Response> updateProduct(Long productId, AddProduct addProduct);

	ResponseEntity<Response> fetchProductDetails(Integer pageNo, Integer pageSize);

}
