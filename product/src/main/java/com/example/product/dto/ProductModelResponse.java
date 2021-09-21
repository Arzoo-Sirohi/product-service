package com.example.product.dto;

public class ProductModelResponse {

	private Long productId;

	private String productName;

	private Long productCost;

	private Boolean availableForSale;

	public ProductModelResponse() {
		super();
	}

	public ProductModelResponse(Long productId, String productName, Long productCost, Boolean availableForSale) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCost = productCost;
		this.availableForSale = availableForSale;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getProductCost() {
		return productCost;
	}

	public void setProductCost(Long productCost) {
		this.productCost = productCost;
	}

	public Boolean getAvailableForSale() {
		return availableForSale;
	}

	public void setAvailableForSale(Boolean availableForSale) {
		this.availableForSale = availableForSale;
	}

}
