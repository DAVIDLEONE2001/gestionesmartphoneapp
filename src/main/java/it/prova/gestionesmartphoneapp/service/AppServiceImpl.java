package it.prova.gestionesmartphoneapp.service;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionesmartphoneapp.dao.AppDAO;
import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.dao.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.model.App;

public class AppServiceImpl implements AppService {

	private AppDAO appDAO;
	private SmartphoneDAO smartphoneDAO;

	@Override
	public List<App> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			appDAO.setEntityManager(entityManager);

			return appDAO.list();

		} catch (Exception e) {

			e.printStackTrace();
			throw e;

		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public App caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			appDAO.setEntityManager(entityManager);

			return appDAO.get(id);

		} catch (Exception e) {

			e.printStackTrace();
			throw e;

		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(App o) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			appDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			appDAO.update(o);
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;

		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void inserisciNuovo(App o) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			appDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			appDAO.insert(o);
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;

		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void rimuovi(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			appDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			appDAO.delete(appDAO.get(id));
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;

		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void setSmartphoeDAO(SmartphoneDAO smartphoneDAO) {

		this.smartphoneDAO = smartphoneDAO;
		
	}

	@Override
	public void setAppDAO(AppDAO appDAO) {

		this.appDAO = appDAO;

	}
	
	@Override
	public App findByNome(String nome) throws Exception{
		
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			appDAO.setEntityManager(entityManager);

			return appDAO.findByNome(nome);

		} catch (Exception e) {

			e.printStackTrace();
			throw e;

		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiornaVersione(App app, int versione) throws Exception {
		
		if (app.getId()==null||app==null){
			throw new RuntimeException("Errore input");
		}
		
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			appDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			app.setVersione(versione);
			app.setDataUltimoAggiornamento(LocalDate.now());
			appDAO.update(app);
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;

		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}

}
