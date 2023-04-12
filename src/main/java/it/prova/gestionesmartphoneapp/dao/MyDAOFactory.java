package it.prova.gestionesmartphoneapp.dao;

public class MyDAOFactory {

	// rendiamo questo factory SINGLETON
	private static SmartphoneDAO SMARTPHONE_DAO_INSTANCE = null;
	private static AppDAO APP_DAO_DAO_INSTANCE = null;

	public static SmartphoneDAO getSmartphoneDAOInstance() {
		if (SMARTPHONE_DAO_INSTANCE == null)
			SMARTPHONE_DAO_INSTANCE = new SmartphoneDAOImpl();
		return SMARTPHONE_DAO_INSTANCE;
	}

	public static AppDAO getAppDAOInstance() {
		if (APP_DAO_DAO_INSTANCE == null)
			APP_DAO_DAO_INSTANCE = new AppDAOImpl();
		return APP_DAO_DAO_INSTANCE;
	}

}
