package com.example.product.dto;

public class AddProduct {
	
	private String productName;

	private long productCost;
	
	
	private Boolean availableForSale;


	
	public AddProduct() {
		super();
	}


	public AddProduct(String productName, long productCost, Boolean availableForSale) {
		super();
		this.productName = productName;
		this.productCost = productCost;
		this.availableForSale = availableForSale;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public long getProductCost() {
		return productCost;
	}


	public void setProductCost(long productCost) {
		this.productCost = productCost;
	}


	public Boolean getAvailableForSale() {
		return availableForSale;
	}


	public void setAvailableForSale(Boolean availableForSale) {
		this.availableForSale = availableForSale;
	}
	


}
