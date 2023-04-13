package it.prova.gestionesmartphoneapp.dao;

import it.prova.gestionesmartphoneapp.model.Smartphone;

public interface SmartphoneDAO extends IBaseDAO<Smartphone> {

//	public void aggiornaOS(Smartphone smartphoneEsistente, int nuovaVersione);

	public Smartphone findByIdFetchinApp(Long id)throws Exception;
	
}
