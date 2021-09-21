package com.example.product;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.product.dao.ProductDaoImpl;
import com.example.product.dto.AddProduct;

public class ProductDaoTest {

	@Mock
	private SessionFactory mockedSessionFactory;
	private Session mockedSession;
	private Transaction mockedTransaction;
	private Query query;
	private ProductDaoImpl productDaoImpl;

	private ProductModel productModel;

	@MockBean
	private NativeQuery nativeQuery;

	@SuppressWarnings("unchecked")
	@Before
	public void initiateSessionFactoryMock() {
		productDaoImpl = new ProductDaoImpl();
		query = Mockito.mock(Query.class);
		nativeQuery = Mockito.mock(NativeQuery.class);
		mockedSessionFactory = Mockito.mock(SessionFactory.class);
		mockedSession = Mockito.mock(Session.class);
		mockedTransaction = Mockito.mock(Transaction.class);
		ReflectionTestUtils.setField(productDaoImpl, "sessionFactory", mockedSessionFactory);
	}

	/**
	 * This method is used to check failure response of getNotifyEmailsByAlarmRuleId
	 * method.
	 */
	@Test
	public void productDelete() {
		final List<String> productList = new ArrayList();
		String hql = "DELETE DeviceProduct WHERE productName IN :productName";
		when(mockedSessionFactory.openSession()).thenReturn(mockedSession);
		when(mockedSession.createQuery(hql)).thenReturn(query);
		when(query.list()).thenReturn(productList);
		Boolean deleteProduct = productDaoImpl.deleteProduct(productList);
		assertEquals(true, deleteProduct);
	}

	/**
	 * This method is used to check failure response of getNotifyEmailsByAlarmRuleId
	 * method.
	 */
	@Test
	public void fetchDetails() {
		final List<Object[]> productList = new ArrayList();
		String hql = "select p.product_name,p.product_id,p.product_cost,p.product_sale";
		when(mockedSessionFactory.openSession()).thenReturn(null);
		when(mockedSession.createNativeQuery(hql)).thenReturn(nativeQuery);
		when(query.list()).thenReturn(productList);
		List<Object[]> fetchProductDetails = productDaoImpl.fetchProductDetails(1, 1);
		assertEquals(productList, fetchProductDetails);
	}

	/**
	 * This method is used to check failure response of getNotifyEmailsByAlarmRuleId
	 * method.
	 */
	@Test
	public void updateProduct() {
		AddProduct addProduct = new AddProduct();
		when(mockedSessionFactory.openSession()).thenReturn(mockedSession);
		when(mockedSession.beginTransaction()).thenReturn(mockedTransaction);
		Boolean updateProduct = productDaoImpl.updateProduct(addProduct);
		assertEquals(true, updateProduct);
	}
	
	/**
	 * This method is used to check failure response of getNotifyEmailsByAlarmRuleId
	 * method.
	 */
	@Test
	public void addProduct() {
		AddProduct addProduct = new AddProduct();
		when(mockedSessionFactory.openSession()).thenReturn(mockedSession);
		when(mockedSession.beginTransaction()).thenReturn(mockedTransaction);
		Boolean updateProduct = productDaoImpl.addProduct(addProduct);
		assertEquals(true, updateProduct);
	}
	
	@Test
	public void addProductFailure() {
		AddProduct addProduct = new AddProduct();
		when(mockedSessionFactory.openSession()).thenReturn(mockedSession);
		when(mockedSession.beginTransaction()).thenReturn(mockedTransaction);
		Boolean updateProduct = productDaoImpl.addProduct(null);
		assertEquals(true, updateProduct);
	}

}
