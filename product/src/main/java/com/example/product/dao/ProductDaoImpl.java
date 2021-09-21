package com.example.product.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.product.dto.AddProduct;

@Component
public class ProductDaoImpl implements ProductDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Boolean addProduct(AddProduct addProduct) {
		try (final Session session = sessionFactory.openSession()) {
			// Begin transaction
			final Transaction tr = session.beginTransaction();
			session.saveOrUpdate(addProduct);
			tr.commit();
			return true;

		} catch (HibernateException e) {
			return false;
		}
	}

	@Override
	public Boolean deleteProduct(List<String> productName) {

		try (final Session session = sessionFactory.openSession()) {
			final Transaction tr = session.beginTransaction();
			final Query query = session.createQuery("DELETE DeviceProduct WHERE productName IN :productName");
			query.setParameter("productName", productName);
			query.executeUpdate();
			tr.commit();
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}

	@Override
	public Boolean fetchProductId(Long productId) {
		try (final Session session = sessionFactory.openSession()) {
			final String hql = "Select * from product p where p.productId = :productId";
			final Query query = session.createNativeQuery(hql);
			query.setParameter("productId", productId);
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	@Override
	public Boolean updateProduct(AddProduct addProduct) {
		Transaction tr = null;
		try (final Session session = sessionFactory.openSession()) {
			// Begin transaction
			tr = session.beginTransaction();
			session.saveOrUpdate(addProduct);
			tr.commit();
			return true;

		} catch (HibernateException e) {
			return false;
		}
	}

	@Override
	public List<Object[]> fetchProductDetails(Integer pageNo, Integer pageSize) {
		try (final Session session = sessionFactory.openSession()) {

			// Get current session Object from SessionFactory Class.
			String hql = "select p.product_name,p.product_id,p.product_cost,p.product_sale";

			// setting limit and offset
			if (pageNo != 0 || pageSize != 0) {
				hql = hql + "LIMIT " + pageSize;
				hql = hql + " OFFSET " + (pageNo > 0 ? (pageNo - 1) * pageSize : pageNo * pageSize);
			}

			// create native query
			final Query query = session.createNativeQuery(hql);

			// Returning list of object.
			return query.list();

		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

}
