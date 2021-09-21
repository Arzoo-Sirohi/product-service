package com.example.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long productId;

	@Column(name = "product_name", length = 20)
	private String productName;

	@Column(name = "product_cost")
	private Long productCost;

	@Column(name = "product_sale")
	private Boolean availableForSale;

	public ProductModel() {
		super();
	}

	public ProductModel(Long productId, String productName, Long productCost, Boolean availableForSale) {
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
