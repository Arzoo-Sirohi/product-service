package com.example.product.dao;

import java.util.List;

import com.example.product.dto.AddProduct;

public interface ProductDao {

	public Boolean addProduct(AddProduct addProduct);

	public Boolean deleteProduct(List<String> productName);

	public Boolean fetchProductId(Long productId);

	public Boolean updateProduct(AddProduct addProduct);

	public List<Object[]> fetchProductDetails(Integer pageNo, Integer pageSize);

}
