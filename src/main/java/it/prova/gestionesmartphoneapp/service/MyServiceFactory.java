package it.prova.gestionesmartphoneapp.service;

import it.prova.gestionesmartphoneapp.dao.MyDAOFactory;

public class MyServiceFactory {

	// rendiamo questo factory SINGLETON
	private static SmartphoneService SMARTPHONE_SERVICE_INSTANCE;
	private static AppService APP_SERVICE_INSTANCE;

	public static SmartphoneService getSmartphoneServiceInstance() {
		if (SMARTPHONE_SERVICE_INSTANCE == null)
			SMARTPHONE_SERVICE_INSTANCE = new SmartphoneServiceImpl();

		SMARTPHONE_SERVICE_INSTANCE.setSmartphoeDAO(MyDAOFactory.getSmartphoneDAOInstance());
		SMARTPHONE_SERVICE_INSTANCE.setAppDAO(MyDAOFactory.getAppDAOInstance());
		return SMARTPHONE_SERVICE_INSTANCE;
	}

	public static AppService getAppServiceInstance() {
		if (APP_SERVICE_INSTANCE == null)
			APP_SERVICE_INSTANCE = new AppServiceImpl();

		APP_SERVICE_INSTANCE.setSmartphoeDAO(MyDAOFactory.getSmartphoneDAOInstance());
		APP_SERVICE_INSTANCE.setAppDAO(MyDAOFactory.getAppDAOInstance());
		return APP_SERVICE_INSTANCE;
	}

}
