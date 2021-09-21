package com.example.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.dto.AddProduct;
import com.example.product.dto.DeleteProduct;
import com.example.product.dto.Response;
import com.example.product.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	ProductService productService;

	/**
	 * This method is used to fetch the product details with pagination
	 * 
	 * 
	 * @return ResponseEntity<Response>
	 */
	@PutMapping(value = "/fetch-product", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Response> fetchProduct(@RequestParam(defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(defaultValue = "0", required = false) Integer pageSize) {
		try {

			return productService.fetchProductDetails(pageNo, pageSize);
		} catch (final Exception e) {
			return new ResponseEntity<>(new Response(false, "FAILED_TO_ADD_PRODUCT", null), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * This method is used to register the product
	 * 
	 * 
	 * @return ResponseEntity<Response>
	 */
	@PostMapping(value = "/add-product", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Response> addDevices(@RequestBody AddProduct addProduct) {
		try {

			if (null == addProduct) {
				return new ResponseEntity<>(new Response(false, "INVALID_REQUEST_BODY", null), HttpStatus.BAD_REQUEST);
			}
			return productService.addProduct(addProduct);
		} catch (final Exception e) {
			return new ResponseEntity<>(new Response(false, "FAILED_TO_ADD_PRODUCT", null), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * This method is used to delete the product
	 * 
	 * 
	 * @return ResponseEntity<Response>
	 */
	@DeleteMapping(value = "/delete-product", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Response> addDevices(@RequestBody DeleteProduct deleteProduct) {
		try {

			if (null == deleteProduct) {
				return new ResponseEntity<>(new Response(false, "INVALID_REQUEST_BODY", null), HttpStatus.BAD_REQUEST);
			}
			return productService.deleteProduct(deleteProduct);
		} catch (final Exception e) {
			return new ResponseEntity<>(new Response(false, "FAILED_TO_ADD_PRODUCT", null), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * This method is used to update the product
	 * 
	 * 
	 * @return ResponseEntity<Response>
	 */
	@PutMapping(value = "/update-product", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Response> updateProduct(
			@RequestHeader(name = "product-id", required = true) final Long productId,
			@RequestBody AddProduct addProduct) {
		try {

			if (null == productId) {
				return new ResponseEntity<>(new Response(false, "PRODUCT_ID_INVALUD", null), HttpStatus.BAD_REQUEST);
			}
			return productService.updateProduct(productId, addProduct);

		} catch (final Exception e) {
			return new ResponseEntity<>(new Response(false, "FAILED_TO_ADD_PRODUCT", null), HttpStatus.BAD_REQUEST);
		}
	}

}
