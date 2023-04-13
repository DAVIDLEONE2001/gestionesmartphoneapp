package it.prova.gestionesmartphoneapp.dao;

import it.prova.gestionesmartphoneapp.model.App;

public interface AppDAO extends IBaseDAO<App> {

	public App findByNome (String nome) throws Exception ;
	public App findByIdFetchingSmartphones(Long id)throws Exception;
	/* @Override
	public Utente findByIdFetchingRuoli(Long id) {
		TypedQuery<Utente> query = entityManager
				.createQuery("select u FROM Utente u left join fetch u.ruoli r where u.id = :idUtente", Utente.class);
		query.setParameter("idUtente", id);
		return query.getResultList().stream().findFirst().orElse(null);
	} */
	
}
