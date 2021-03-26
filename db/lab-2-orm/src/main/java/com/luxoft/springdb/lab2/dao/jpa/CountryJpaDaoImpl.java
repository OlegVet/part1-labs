package com.luxoft.springdb.lab2.dao.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.luxoft.springdb.lab2.dao.CountryDao;
import com.luxoft.springdb.lab2.model.Country;

import javax.persistence.EntityManager;

@Repository
public class CountryJpaDaoImpl extends AbstractJpaDao implements CountryDao {

	@Override
	public void save(Country country) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(country);
		entityManager.getTransaction().commit();
	}

	@Override
	public List<Country> getAllCountries() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager.createQuery("from Country", Country.class).getResultList();
	}

	@Override
	public Country getCountryByName(String name) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager.createQuery("select c from Country c where c.name like :name", Country.class)
				.setParameter("name", name)
				.getSingleResult();
	}

	@Override
	public Country getCountryByCode(String code) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager.createQuery("select c from Country c where c.codeName like :code", Country.class)
				.setParameter("code", code)
				.getSingleResult();
	}

}
