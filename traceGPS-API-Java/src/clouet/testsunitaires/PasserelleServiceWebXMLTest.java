package clouet.testsunitaires;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import org.junit.internal.MethodSorter;
import org.junit.runners.MethodSorters;

import clouet.classes.Outils;
import clouet.classes.PasserelleServicesWebXML;
import clouet.classes.Point;
import clouet.classes.PointDeTrace;
import clouet.classes.Trace;
import clouet.classes.Utilisateur;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PasserelleServiceWebXMLTest {
	
	@Before
	public void setUp() throws Exception
	{
		
	}
	@Test
	public void T1_testConnecter()
	{
		System.out.println("TEST CONNECTER");
		System.out.println(" ");
		
		String msg = "";
		msg = PasserelleServicesWebXML.connecter("admin", Outils.sha1("adminnnnnnnn"));
		assertEquals("Erreur : authentification incorrecte.", msg);
		msg = PasserelleServicesWebXML.connecter("admin", Outils.sha1("mdpadmin"));
		assertEquals("Administrateur authentifié.", msg);
		msg = PasserelleServicesWebXML.connecter("europa", Outils.sha1("mdputilisateur"));
		assertEquals("Utilisateur authentifié.", msg);
		
		System.out.println(" ");
	}
	

		
	@Test
	public void T2_testCreerUnUtilisateur()
	{
		System.out.println("TEST CREER UN UTILISATEUR");
		System.out.println(" ");
		
		String msg = "";
		msg = PasserelleServicesWebXML.creerUnUtilisateur("jim", "delasalle.sio.eleves@gmail.com", "1122334455");
		assertEquals("Erreur : pseudo trop court (8 car minimum) ou déjà existant.", msg);
		msg = PasserelleServicesWebXML.creerUnUtilisateur("turlututu", "delasalle.sio.elevesgmail.com", "1122334455");
		assertEquals("Erreur : adresse mail incorrecte ou déjà existante.", msg);
		msg = PasserelleServicesWebXML.creerUnUtilisateur("turlututu", "delasalle.sio.eleves@gmailcom", "1122334455");
		assertEquals("Erreur : adresse mail incorrecte ou déjà existante.", msg);
		msg = PasserelleServicesWebXML.creerUnUtilisateur("turlututu", "delasalle.sio.eleves@gmail.com", "1122334455");
		assertEquals("Erreur : adresse mail incorrecte ou déjà existante.", msg);
		msg = PasserelleServicesWebXML.creerUnUtilisateur("turlututu", "delasallesioeleves@gmail.com", "1122334455");
		assertEquals("Enregistrement effectué ; vous allez recevoir un courriel avec votre mot de passe.", msg);
		msg = PasserelleServicesWebXML.creerUnUtilisateur("turlututu", "de.la.salle.sio.eleves@gmail.com", "1122334455");
		assertEquals("Erreur : pseudo trop court (8 car minimum) ou déjà existant.", msg);
		
		System.out.println(" ");
	}
	
	@Test
	public void T3_testSupprimerUnUtilisateur()
	{
		System.out.println("TEST SUPPRIMER UN UTILISATEUR");
		System.out.println(" ");
		
		String msg;
		msg = PasserelleServicesWebXML.supprimerUnUtilisateur("europa", Outils.sha1("mdputilisateurrrrrr"), "toto");
		assertEquals("Erreur : authentification incorrecte.", msg);
		msg = PasserelleServicesWebXML.supprimerUnUtilisateur("europa", Outils.sha1("mdputilisateur"), "toto");
		assertEquals("Erreur : authentification incorrecte.", msg);
		msg = PasserelleServicesWebXML.supprimerUnUtilisateur("admin", Outils.sha1("mdpadminnnnn"), "toto");
		assertEquals("Erreur : authentification incorrecte.", msg);
		msg = PasserelleServicesWebXML.supprimerUnUtilisateur("admin", Outils.sha1("mdpadmin"), "toto");
		assertEquals("Erreur : pseudo utilisateur inexistant.", msg);
		msg = PasserelleServicesWebXML.supprimerUnUtilisateur("admin", Outils.sha1("mdpadmin"), "neon");
		assertEquals("Erreur : suppression impossible ; cet utilisateur possède encore des traces.", msg);
		msg = PasserelleServicesWebXML.supprimerUnUtilisateur("admin", Outils.sha1("mdpadmin"), "turlututu");
		assertEquals("Suppression effectuée ; un courriel va être envoyé à l'utilisateur.", msg);
		
		System.out.println(" ");
	}
	
	/*
	@Test
	public void T4_testChangerDeMdp()
	{
		System.out.println("TEST CHANGER DE MDP");
		System.out.println(" ");
		
		String msg = PasserelleServicesWebXML.changerDeMdp("europa", Outils.sha1("mdputilisateur"), 
				"passepasse", "passepassepasse");
		assertEquals("Erreur : le nouveau mot de passe et sa confirmation sont différents.", msg);
		msg = PasserelleServicesWebXML.changerDeMdp("europa", Outils.sha1("mdputilisateurrrr"), 
				"passepasse", "passepasse");
		assertEquals("Erreur : authentification incorrecte.", msg);
		msg = PasserelleServicesWebXML.changerDeMdp("europa", Outils.sha1("mdputilisateur"), 
				"mdputilisateurrrr", "mdputilisateurrrr");
		assertEquals("Enregistrement effectué ; vous allez recevoir un courriel de confirmation.", msg);
		
		msg = PasserelleServicesWebXML.changerDeMdp("europa", Outils.sha1("mdputilisateurrrr"), 
				"mdputilisateur", "mdputilisateur");
		assertEquals("Enregistrement effectué ; vous allez recevoir un courriel de confirmation.", msg);
		
	}
	*/
	/*
	@Test
	public void T5_testDemanderMdp()
	{
		System.out.println("TEST DEMANDER MDP");
		System.out.println(" ");
		
		String msg = PasserelleServicesWebXML.demanderMdp("jim");
		assertEquals("Erreur : pseudo inexistant.", msg);
		msg = PasserelleServicesWebXML.demanderMdp("europa");
		assertEquals("Vous allez recevoir un courriel avec votre nouveau mot de passe.", msg);
	}
	*/

	
	@Test
	public void T6_TestDemanderUneAutorisation()
	{
		System.out.println("TEST DEMANDER UNE AUTORISATION");
		System.out.println(" ");
		
		String msg = PasserelleServicesWebXML.demanderUneAutorisation("europa", 
		Outils.sha1("mdputilisateur"), "toto", "", "");
		//assertEquals("Erreur : données incomplètes.", msg);
		msg = PasserelleServicesWebXML.demanderUneAutorisation("europa", Outils.sha1("mdputilisateurrrrrr"),
		"toto", "coucou", "charles-edouard");
		assertEquals("Erreur : authentification incorrecte.", msg);
		msg = PasserelleServicesWebXML.demanderUneAutorisation("europa", Outils.sha1("mdputilisateur"), "toto", 
		"coucou", "charles-edouard");
		assertEquals("Erreur : pseudo utilisateur inexistant.", msg);
		msg = PasserelleServicesWebXML.demanderUneAutorisation("europa", Outils.sha1("mdputilisateur"), 
		"galileo", "coucou", "charles-edouard");
		assertEquals("galileo va recevoir un courriel avec votre demande.", msg);
	}	
	
	@Test
	public void T7_testRetirerUneAutorisation() {
	String msg = PasserelleServicesWebXML.retirerUneAutorisation("europa", Outils.sha1("mdputilisateurrrrrr"), 
	"toto", "coucou");
	assertEquals("Erreur : authentification incorrecte.", msg);
	msg = PasserelleServicesWebXML.retirerUneAutorisation("europa", Outils.sha1("mdputilisateur"), "toto", 
	"coucou");
	assertEquals("Erreur : pseudo utilisateur inexistant.", msg);
	msg = PasserelleServicesWebXML.retirerUneAutorisation("europa", Outils.sha1("mdputilisateur"), "juno", 
	"coucou");
	assertEquals("Erreur : l'autorisation n'était pas accordée.", msg);
	msg = PasserelleServicesWebXML.retirerUneAutorisation("neon", Outils.sha1("mdputilisateur"), "oxygen", 
	"coucou");
	assertEquals("Autorisation supprimée ; oxygen va recevoir un courriel de notification.", msg);
	msg = PasserelleServicesWebXML.retirerUneAutorisation("neon", Outils.sha1("mdputilisateur"), "photon", "");
	assertEquals("Autorisation supprimée.", msg);
	}
	
	@Test
	public void T8_testEnvoyerPosition() throws ParseException
	{
		
		System.out.println("TEST ENVOYER POSITION");
		System.out.println(" ");
		Date laDate = Outils.convertirEnDateHeure("24/01/2018 13:42:21");
		PointDeTrace lePoint = new PointDeTrace(23, 0, 48.15, -1.68, 50, laDate, 80);
		String msg = PasserelleServicesWebXML.envoyerPosition("europa", Outils.sha1("mdputilisateurrrrrr"), lePoint);
		assertEquals("Erreur : authentification incorrecte.", msg);
		lePoint = new PointDeTrace(2333, 0, 48.15, -1.68, 50, laDate, 80);
		msg = PasserelleServicesWebXML.envoyerPosition("europa", Outils.sha1("mdputilisateur"), lePoint);
		assertEquals("Erreur : le numéro de trace n'existe pas.", msg);
		lePoint = new PointDeTrace(22, 0, 48.15, -1.68, 50, laDate, 80);
		msg = PasserelleServicesWebXML.envoyerPosition("europa", Outils.sha1("mdputilisateur"), lePoint);
		assertEquals("Erreur : le numéro de trace ne correspond pas à cet utilisateur.", msg);
		lePoint = new PointDeTrace(4, 0, 48.15, -1.68, 50, laDate, 80);
		msg = PasserelleServicesWebXML.envoyerPosition("europa", Outils.sha1("mdputilisateur"), lePoint);
		assertEquals("Point créé.", msg);
	}


	@Test
	public void T9_testDemarrerEnregistrementParcours() {
	Trace laTrace = new Trace();
	String msg = PasserelleServicesWebXML.demarrerEnregistrementParcours("europa", 
	Outils.sha1("mdputilisateurrrrrr"), laTrace);
	assertEquals("Erreur : authentification incorrecte.", msg);
	laTrace = new Trace();
	msg = PasserelleServicesWebXML.demarrerEnregistrementParcours("europa", 
	Outils.sha1("mdputilisateur"), laTrace);
	assertEquals("Trace créée.", msg);
	}


	@Test
	public void T10_testArreterEnregistrementParcours() {
	String msg;
	msg = PasserelleServicesWebXML.arreterEnregistrementParcours("europa", Outils.sha1("mdputilisateurrrrrr"), 23);
	assertEquals("Erreur : authentification incorrecte.", msg);
	msg = PasserelleServicesWebXML.arreterEnregistrementParcours("europa", Outils.sha1("mdputilisateur"), 230);
	assertEquals("Erreur : parcours inexistant.", msg);
	msg = PasserelleServicesWebXML.arreterEnregistrementParcours("europa", Outils.sha1("mdputilisateur"), 5);
	assertEquals("Erreur : le numéro de trace ne correspond pas à cet utilisateur.", msg);
	msg = PasserelleServicesWebXML.arreterEnregistrementParcours("europa", Outils.sha1("mdputilisateur"), 4);
	assertEquals("Erreur : cette trace est déjà terminée.", msg);
	msg = PasserelleServicesWebXML.arreterEnregistrementParcours("europa", Outils.sha1("mdputilisateur"), 23);
	assertEquals("Enregistrement terminé.", msg);
	}

	
	@Test
	public void T11_testSupprimerUnUnParcours() {
	String msg = PasserelleServicesWebXML.supprimerUnParcours("europa", Outils.sha1("mdputilisateurrrrrr"), 10);
	assertEquals("Erreur : authentification incorrecte.", msg);
	msg = PasserelleServicesWebXML.supprimerUnParcours("europa", Outils.sha1("mdputilisateur"), 100);
	assertEquals("Erreur : parcours inexistant.", msg);
	msg = PasserelleServicesWebXML.supprimerUnParcours("europa", Outils.sha1("mdputilisateur"), 22);
	assertEquals("Erreur : vous n'êtes pas le propriétaire de ce parcours.", msg);
	msg = PasserelleServicesWebXML.supprimerUnParcours("europa", Outils.sha1("mdputilisateur"), 30);
	assertEquals("Parcours supprimé.", msg);
	}
	
} // fin du test
