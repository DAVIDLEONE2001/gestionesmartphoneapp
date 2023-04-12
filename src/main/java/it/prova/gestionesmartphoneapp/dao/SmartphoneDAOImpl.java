package it.prova.gestionesmartphoneapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionesmartphoneapp.model.Smartphone;

public class SmartphoneDAOImpl implements SmartphoneDAO {

	private EntityManager entityManager;

	@Override
	public List<Smartphone> list() throws Exception {
	
		return entityManager.createQuery("from Smartphone", Smartphone.class).getResultList();

	}

	@Override
	public Smartphone get(Long id) throws Exception {
	
		return entityManager.find(Smartphone.class, id);
	}

	@Override
	public void update(Smartphone o) throws Exception {

		if (o == null) {
			throw new Exception("Problema valore input");
		}
		
		entityManager.merge(o);
		
	}

	@Override
	public void insert(Smartphone o) throws Exception {

		if (o == null) {
			
			throw new Exception("Problema valore input");
		}
		
		entityManager.persist(o);
		
	}

	@Override
	public void delete(Smartphone o) throws Exception {

		if (o == null) {
			throw new Exception("Problema valore input");
		}
		
		entityManager.remove(entityManager.merge(o));
		
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {

		this.entityManager = entityManager;

	}

}
