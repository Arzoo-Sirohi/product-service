package com.example.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.product.ProductModel;
import com.example.product.dao.ProductDao;
import com.example.product.dto.AddProduct;
import com.example.product.dto.DeleteProduct;
import com.example.product.dto.ProductModelResponse;
import com.example.product.dto.Response;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public ResponseEntity<Response> addProduct(final AddProduct addProduct) {

		try {
			final ProductModel productModel = new ProductModel();
			productModel.setProductName(addProduct.getProductName());
			productModel.setAvailableForSale(addProduct.getAvailableForSale());
			productModel.setProductCost(addProduct.getProductCost());

			Boolean isProductSaved = productDao.addProduct(addProduct);
			if (isProductSaved) {
				return new ResponseEntity<>(new Response(false, "PRODUCT_ADDED_SUCESSFULLY", null), HttpStatus.OK);
			}
			return new ResponseEntity<>(new Response(false, "FAILED_TO_ADD_PRODUCT_IN_DB", null),
					HttpStatus.BAD_REQUEST);
		} catch (final Exception e) {
			return new ResponseEntity<>(new Response(false, "FAILED_TO_ADD_PRODUCT", null), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Response> deleteProduct(DeleteProduct deleteProduct) {
		try {

			Boolean productDeleted = productDao.deleteProduct(deleteProduct.getProductName());

			if (productDeleted) {
				return new ResponseEntity<>(new Response(false, "PRODUCT_DELETED_SUCCESSFULLY", null), HttpStatus.OK);
			}
			return new ResponseEntity<>(new Response(false, "FAILED_TO_DELETE_PRODUCT", null), HttpStatus.BAD_REQUEST);
		} catch (final Exception e) {
			return new ResponseEntity<>(new Response(false, "FAILED_TO_DELETE_PRODUCT", null), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Response> updateProduct(Long productId, AddProduct addProduct) {
		try {
			Boolean fetchProductId = productDao.fetchProductId(productId);

			if (!fetchProductId) {
				return new ResponseEntity<>(new Response(false, "PRODUCT_ID_NOT_FOUND", null), HttpStatus.NOT_FOUND);
			}
			final ProductModel productModel = new ProductModel();
			productModel.setProductName(addProduct.getProductName());
			productModel.setAvailableForSale(addProduct.getAvailableForSale());
			productModel.setProductCost(addProduct.getProductCost());

			Boolean isProductSaved = productDao.updateProduct(addProduct);
			if (isProductSaved) {
				return new ResponseEntity<>(new Response(false, "PRODUCT_DETAILS_UPDATED", null), HttpStatus.OK);
			}
			return new ResponseEntity<>(new Response(false, "FAILED_TO_UPDATE_PRODUCT", null), HttpStatus.BAD_REQUEST);
		} catch (final Exception e) {
			return new ResponseEntity<>(new Response(false, "EXCEPTION_WHILE_UPDATING_PRODUCT", null),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Response> fetchProductDetails(Integer pageNo, Integer pageSize) {
		try {
			final List<Object[]> productList = productDao.fetchProductDetails(pageNo, pageSize);
			if (null == productList) {
				return new ResponseEntity<>(new Response(false, "PRODUCT_DATA_NOT_FOUND_IN_DB", null),
						HttpStatus.BAD_REQUEST);
			}
			List<ProductModelResponse> list = new ArrayList<>();

			// Iterating alarm list and setting values.
			for (final Object[] product : productList) {

				// Initializing alarmModelResponse.
				final ProductModelResponse productModel = new ProductModelResponse();
				productModel.setProductName((String) product[0]);
				productModel.setProductId((Long) product[1]);
				productModel.setProductCost((Long) product[2]);
				productModel.setAvailableForSale((Boolean) product[3]);

				// Adding values to alarmList.
				list.add(productModel);
			}

			return new ResponseEntity<>(new Response(true, "PRODUCT__FETCH_SUCCESS", list), HttpStatus.OK);
		} catch (final Exception e) {
			return new ResponseEntity<>(new Response(false, "EXCEPTION_WHILE_FETCHING_PRODUCT", null),
					HttpStatus.BAD_REQUEST);
		}

	}

}
