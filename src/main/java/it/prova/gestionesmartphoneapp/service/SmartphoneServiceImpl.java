package it.prova.gestionesmartphoneapp.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionesmartphoneapp.dao.AppDAO;
import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.dao.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;

public class SmartphoneServiceImpl implements SmartphoneService {

	private AppDAO appDAO;
	private SmartphoneDAO smartphoneDAO;

	@Override
	public List<Smartphone> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			smartphoneDAO.setEntityManager(entityManager);

			return smartphoneDAO.list();

		} catch (Exception e) {

			e.printStackTrace();
			throw e;

		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Smartphone caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			smartphoneDAO.setEntityManager(entityManager);

			return smartphoneDAO.get(id);

		} catch (Exception e) {

			e.printStackTrace();
			throw e;

		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Smartphone o) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			smartphoneDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			smartphoneDAO.update(o);
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
	public void inserisciNuovo(Smartphone o) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			smartphoneDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			smartphoneDAO.insert(o);
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

			smartphoneDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			smartphoneDAO.delete(smartphoneDAO.get(id));
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
	public void aggiornaOS(Smartphone smartphoneEsistente, int nuovaVersione) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		if (smartphoneEsistente == null || smartphoneEsistente.getId() == null) {

			throw new RuntimeException("Errore con input");
		}

		try {

			smartphoneDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			smartphoneEsistente.setVersioneOS(nuovaVersione);
			smartphoneDAO.update(smartphoneEsistente);
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
	public void installaAppSuSmartPhoneEsistente(Smartphone smartphoneEsistente, App appEsistente) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		if (smartphoneEsistente == null || smartphoneEsistente.getId() == null || appEsistente.getId() == null
				|| appEsistente == null) {

			throw new RuntimeException("Errore con input");
		}

		try {

			smartphoneDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			smartphoneEsistente.getApps().add(appEsistente);
			smartphoneDAO.update(smartphoneEsistente);
			appEsistente.getSmartphones().add(smartphoneEsistente);
			appDAO.update(appEsistente);
			smartphoneDAO.update(smartphoneEsistente);
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
