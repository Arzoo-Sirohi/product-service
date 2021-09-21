package com.example.product.dto;

import java.util.List;

public class DeleteProduct {

	List<String> productName;

	public DeleteProduct() {
		super();
	}

	public DeleteProduct(List<String> productName) {
		super();
		this.productName = productName;
	}

	public List<String> getProductName() {
		return productName;
	}

	public void setProductName(List<String> productName) {
		this.productName = productName;
	}

}
