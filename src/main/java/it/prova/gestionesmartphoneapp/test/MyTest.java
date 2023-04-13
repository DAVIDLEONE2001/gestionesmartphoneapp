package it.prova.gestionesmartphoneapp.test;

import java.time.LocalDate;

import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;
import it.prova.gestionesmartphoneapp.service.AppService;
import it.prova.gestionesmartphoneapp.service.MyServiceFactory;
import it.prova.gestionesmartphoneapp.service.SmartphoneService;

public class MyTest {

	public static void main(String[] args) {

		AppService appServiceIstance = MyServiceFactory.getAppServiceInstance();
		SmartphoneService smartphoneServiceIstance = MyServiceFactory.getSmartphoneServiceInstance();

		try {

			initApp(appServiceIstance);

			// *****************INIZIO TEST SMARTPHONE****************************

			System.out.println(
					"Nella tabella smartphone ci sono " + smartphoneServiceIstance.listAll().size() + " elementi");

//			testInsertSmartphone(smartphoneServiceIstance);
//			testAggiornaOS(smartphoneServiceIstance);
			testInstallaAppSuSmartPhoneEsistente(smartphoneServiceIstance, appServiceIstance);

			System.out.println(
					"Nella tabella smartphone ci sono " + smartphoneServiceIstance.listAll().size() + " elementi");

			// *****************FINE TEST SMARTPHONE****************************

			// *****************INIZIO TEST APP****************************

			System.out.println("Nella tabella app ci sono " + appServiceIstance.listAll().size() + " elementi");

//			testInsertApp(appServiceIstance);
//			testAggiornaVersione(appServiceIstance);

			System.out.println("Nella tabella app ci sono " + appServiceIstance.listAll().size() + " elementi");

			// *****************FINE TEST APP****************************

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}

	}

	private static void initApp(AppService appServiceIstance) throws Exception {

		if (appServiceIstance.findByNome("SputyFai") == null) {
			appServiceIstance.inserisciNuovo(new App("SputyFai", LocalDate.now(), LocalDate.parse("2020-01-21"), 1));
		}

		if (appServiceIstance.findByNome("Ammazonn") == null) {
			appServiceIstance.inserisciNuovo(new App("Ammazonn", LocalDate.now(), LocalDate.parse("2020-01-21"), 1));
		}

	}

	// *******************************INIZIO TEST Smartphone**************

	public static void testInsertSmartphone(SmartphoneService smartphoneServiceIstance) throws Exception {

		System.out.println("***********INIZIO TEST testInsertSmartphone************");

		Smartphone telefono = new Smartphone("Samsung", "A20", 270, 1);
		smartphoneServiceIstance.inserisciNuovo(telefono);
		if (telefono.getId() == null) {
			throw new RuntimeException(
					"****************TEST FAILED testInsertSmartphone: telefono non aggiunta**********");

		}

		System.out.println("***********FINE TEST testInsertSmartphone: PASSED************");

		smartphoneServiceIstance.rimuovi(telefono.getId());

	}

	private static void testAggiornaOS(SmartphoneService smartphoneServiceIstance) throws Exception {

		System.out.println("***********INIZIO TEST testAggiornaOS************");

		Smartphone telefono = new Smartphone("Samsung", "A20", 270, 1);
		int vecchiaVersione = telefono.getVersioneOS();
		smartphoneServiceIstance.inserisciNuovo(telefono);
		if (telefono.getId() == null) {
			throw new RuntimeException("****************TEST FAILED testAggiornaOS: telefono non aggiunta**********");

		}

		smartphoneServiceIstance.aggiornaOS(telefono, 2);

		int nuovaVersione = telefono.getVersioneOS();

		if (vecchiaVersione == nuovaVersione) {

			throw new RuntimeException("****************TEST FAILED testAggiornaOS: OS non aggiornato**********");

		}

		System.out.println("***********FINE TEST testAggiornaOS: PASSED************");

		smartphoneServiceIstance.rimuovi(telefono.getId());

	}

//	public static void testDisinstallaAppSuSmartPhoneEsistente testato a fine metodo
	public static void testInstallaAppSuSmartPhoneEsistente(SmartphoneService smartphoneServiceIstance,
			AppService appServiceIstance) throws Exception {

		System.out.println("***********INIZIO TEST testInsertSmartphone************");

		Smartphone telefono = new Smartphone("Samsung", "A20", 270, 1);
		if(telefono.getId() != null) {
			smartphoneServiceIstance.aggiorna(telefono);
		} else
		smartphoneServiceIstance.inserisciNuovo(telefono);
		App app = new App("FaiDaTe", LocalDate.now(), LocalDate.parse("2023-01-21"), 1);
		App app2 = new App("FaiDaTe2", LocalDate.now(), LocalDate.parse("2023-01-21"), 1);
		App app3 = new App("FaiDaTe3", LocalDate.now(), LocalDate.parse("2023-01-21"), 1);

		if (appServiceIstance.findByNome("FaiDaTe") == null) {
			appServiceIstance.inserisciNuovo(app);
		} else
			app = appServiceIstance.findByNome("FaiDaTe");

		if (appServiceIstance.findByNome("FaiDaTe2") == null) {
			appServiceIstance.inserisciNuovo(app2);
		} else
			app2 = appServiceIstance.findByNome("FaiDaTe2");

		

		if (appServiceIstance.findByNome("FaiDaTe3") == null) {
			appServiceIstance.inserisciNuovo(app3);
		} else
			app2 = appServiceIstance.findByNome("FaiDaTe3");

//		smartphoneServiceIstance.inserisciNuovo(telefono);

		if (telefono.getId() == null || app.getId() == null || app2.getId() == null || app3.getId() == null) {
			throw new RuntimeException(
					"****************TEST FAILED testInsertSmartphone: telefono o app non aggiunte**********");

		}

		smartphoneServiceIstance.installaAppSuSmartPhoneEsistente(telefono, app);
		smartphoneServiceIstance.installaAppSuSmartPhoneEsistente(telefono, app2);
		smartphoneServiceIstance.installaAppSuSmartPhoneEsistente(telefono, app3);

		Smartphone telefonoReload = smartphoneServiceIstance.caricaSingoloSmartphoneConApp(telefono.getId());
		App appReload = appServiceIstance.caricaSingolaAppConSmartphones(app.getId());
		App appReload2 = appServiceIstance.caricaSingolaAppConSmartphones(app2.getId());
		App appReload3 = appServiceIstance.caricaSingolaAppConSmartphones(app3.getId());

		if (telefonoReload.getApps().isEmpty() || appReload.getSmartphones().isEmpty()
				|| appReload2.getSmartphones().isEmpty() || appReload3.getSmartphones().isEmpty()) {

			throw new RuntimeException(
					"****************TEST FAILED testInsertSmartphone: apps non installate su smartphone o viceversa**********");

		}
		
		

		int numAppDiTelefonoReload = telefonoReload.getApps().size();
		
		smartphoneServiceIstance.disinstallaAppSuSmartPhoneEsistente(telefono, app);
		
		telefonoReload = smartphoneServiceIstance.caricaSingoloSmartphoneConApp(telefono.getId());
		
		if (telefonoReload.getApps().size()==numAppDiTelefonoReload) {// mi aspetto siano 2 prima dell disinstallaAppSuSmartPhoneEsistente
													// erano 3

			throw new RuntimeException(
					"****************TEST FAILED testInsertSmartphone: app non disinstallata**********");

		}

		smartphoneServiceIstance.rimuoviDopoAverDisinstallatoTutto(telefono.getId());
		appServiceIstance.rimuovi(app.getId());
		appServiceIstance.rimuovi(app2.getId());
		appServiceIstance.rimuovi(app3.getId());
		

		if (smartphoneServiceIstance.caricaSingoloElemento(telefono.getId()) == null
				&& appServiceIstance.caricaSingoloElemento(app.getId()) == null
				&& appServiceIstance.caricaSingoloElemento(app2.getId()) == null
				&& appServiceIstance.caricaSingoloElemento(app3.getId()) == null) {

			System.out.println("***********FINE TEST testInsertSmartphone: PASSED************");
		} else {
			throw new RuntimeException(
					"****************TEST FAILED testInsertSmartphone: telefono o app non aggiunta**********");
		}

	}

	// *******************************FINE TEST Smartphone*********
	// --------------------------------------------------------------------------------
	// *******************************INIZIO TEST App**************

	public static void testInsertApp(AppService appServiceIstance) throws Exception {

		System.out.println("***********INIZIO TEST testInsertApp************");

		App app = new App("SputyFai", LocalDate.now(), LocalDate.parse("2020-01-21"), 1);
		appServiceIstance.inserisciNuovo(app);
		if (app.getId() == null) {
			throw new RuntimeException("****************TEST FAILED testInsertApp: app non aggiunta**********");

		}

		System.out.println("***********FINE TEST testInsertApp: PASSED************");

		appServiceIstance.rimuovi(app.getId());

	}

	private static void testAggiornaVersione(AppService appServiceIstance) throws Exception {

		System.out.println("***********INIZIO TEST testAggiornaVersione************");

		App app = new App("SputyFai", LocalDate.now(), LocalDate.parse("2020-01-21"), 1);
		int vecchiaVersione = app.getVersione();
		LocalDate vecchiaDataUltimoAgg = app.getDataUltimoAggiornamento();
		appServiceIstance.inserisciNuovo(app);
		if (app.getId() == null) {
			throw new RuntimeException("****************TEST FAILED testAggiornaVersione: app non aggiunta**********");

		}

		appServiceIstance.aggiornaVersione(app, 2);

		int nuovaVersione = app.getVersione();
		LocalDate nuovaDataUltimoAgg = app.getDataUltimoAggiornamento();

		if (vecchiaVersione == nuovaVersione || vecchiaDataUltimoAgg.equals(nuovaDataUltimoAgg)) {

			throw new RuntimeException(
					"****************TEST FAILED testAggiornaVersione: versione non aggiornata correttamente**********");

		}

		System.out.println("***********FINE TEST testAggiornaVersione: PASSED************");

		appServiceIstance.rimuovi(app.getId());

	}

	// *******************************FINE TEST App****************

}
