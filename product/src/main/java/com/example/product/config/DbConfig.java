package com.example.product.config;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.product.ProductModel;

@Configuration

@EnableTransactionManagement(mode = AdviceMode.PROXY, proxyTargetClass = true)
public class DbConfig {
	@Value("${postgres.db.driver}")
	private String DB_DRIVER;

	@Value("${postgres.db.password}")
	private String DB_PASSWORD;

	@Value("${postgres.db.url}")
	private String DB_URL;

	@Value("${postgres.db.username}")
	private String DB_USERNAME;

	private StandardServiceRegistry registry;

	private SessionFactory sessionFactory;

	@Bean
	public SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

				Map<String, Object> settings = new HashMap<>();
				settings.put(Environment.DRIVER, DB_DRIVER);

				settings.put(Environment.URL, DB_URL);
				settings.put(Environment.USER, DB_USERNAME);
				settings.put(Environment.PASS, DB_PASSWORD);

				// HikariCP settings

				// Maximum waiting time for a connection from the pool

				registryBuilder.applySettings(settings);

				registry = registryBuilder.build();
				MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(ProductModel.class);

				Metadata metadata = sources.getMetadataBuilder().build();
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			} catch (Exception e) {
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
			}
		}
		return sessionFactory;
	}

	public void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

}
