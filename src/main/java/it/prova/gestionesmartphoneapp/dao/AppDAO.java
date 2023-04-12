package it.prova.gestionesmartphoneapp.dao;

import it.prova.gestionesmartphoneapp.model.App;

public interface AppDAO extends IBaseDAO<App> {

	public App findByNome (String nome) throws Exception ; 
	
}
