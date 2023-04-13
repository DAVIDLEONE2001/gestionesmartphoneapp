package it.prova.gestionesmartphoneapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestionesmartphoneapp.model.App;

public class AppDAOImpl implements AppDAO {

	private EntityManager entityManager;

	@Override
	public List<App> list() throws Exception {

		return entityManager.createQuery("from App", App.class).getResultList();

	}

	@Override
	public App get(Long id) throws Exception {

		return entityManager.find(App.class, id);

	}

	@Override
	public void update(App o) throws Exception {

		if (o == null) {
			throw new Exception("Problema valore input");
		}

		entityManager.merge(o);

	}

	@Override
	public void insert(App o) throws Exception {

		if (o == null) {
			throw new Exception("Problema valore input");
		}

		entityManager.persist(o);

	}

	@Override
	public void delete(App o) throws Exception {

		if (o == null) {
			throw new Exception("Problema valore input");
		}

		entityManager.remove(entityManager.merge(o));

	}

	@Override
	public void setEntityManager(EntityManager entityManager) {

		this.entityManager = entityManager;

	}

	@Override
	public App findByNome(String nome) throws Exception {

		TypedQuery<App> query = entityManager.createQuery("select a from App a where a.nome like ?1", App.class).setParameter(1,
				nome);

		return query.getResultStream().findFirst().orElse(null);

	}

	@Override
	public App findByIdFetchingSmartphones(Long id) throws Exception {
		
		TypedQuery<App> query = entityManager
				.createQuery("select a FROM App a left join fetch a.smartphones r where a.id = :idUtente", App.class);
		query.setParameter("idUtente", id);
		return query.getResultList().stream().findFirst().orElse(null);
		
	}

	/* @Override
	public Utente findByIdFetchingRuoli(Long id) {
		TypedQuery<Utente> query = entityManager
				.createQuery("select u FROM Utente u left join fetch u.ruoli r where u.id = :idUtente", Utente.class);
		query.setParameter("idUtente", id);
		return query.getResultList().stream().findFirst().orElse(null);
	} */
}
