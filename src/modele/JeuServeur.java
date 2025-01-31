package modele;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;

import controler.Controle;
import outils.connexion.AsyncResponse;
import outils.connexion.Connection;

/**
 * Gestion du jeu côté serveur
 *
 */
public class JeuServeur extends Jeu {

	/**
	 * Controleur de Jeu redéfini ds la classe fille
	 */
	Controle controle;
	/**
	 * Collection de murs
	 */
	private ArrayList<Mur> lesMurs = new ArrayList<Mur>() ;
	/**
	 * Collection de joueurs
	 */
	private Hashtable<Connection, Joueur> lesJoueurs = new Hashtable<Connection, Joueur>() ;
	
	/**
	 * Constructeur
	 */
	public JeuServeur(Controle controle) {
	}
	
	/**
	 * Réception d'une connexion (pour communiquer avec un ordinateur distant)
	 */
	@Override
	public void connexion(Connection connection, Socket socket, AsyncResponse delegate) {
		lesJoueurs.put(connection, new Joueur()) ;
	}

	@Override
	public void reception(Connection connection, Object info) {
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers tous les clients
	 * fais appel plusieurs fois à l'envoi de la classe Jeu
	 */
	public void envoi() {
	}

	/**
	 * Génération des murs
	 */
	public void constructionMurs() {
	}
	
}
