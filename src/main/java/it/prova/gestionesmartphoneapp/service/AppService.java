package it.prova.gestionesmartphoneapp.service;

import it.prova.gestionesmartphoneapp.dao.AppDAO;
import it.prova.gestionesmartphoneapp.dao.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.model.App;

public interface AppService extends IBaseService<App> {

	public void setSmartphoeDAO (SmartphoneDAO smartphoneDAO);
	public void setAppDAO (AppDAO appDAO);
	public App findByNome(String nome) throws Exception;
	public void aggiornaVersione (App app, int versione) throws Exception;
	
}
