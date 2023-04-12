package it.prova.gestionesmartphoneapp.service;

import it.prova.gestionesmartphoneapp.dao.AppDAO;
import it.prova.gestionesmartphoneapp.dao.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;

public interface SmartphoneService extends IBaseService<Smartphone> {

	public void setSmartphoeDAO(SmartphoneDAO smartphoneDAO);

	public void setAppDAO(AppDAO appDAO);

	public void aggiornaOS(Smartphone smartphoneEsistente, int nuovaVersione) throws Exception;

	public void installaAppSuSmartPhoneEsistente(Smartphone smartphoneEsistente, App appEsistente) throws Exception;

}
