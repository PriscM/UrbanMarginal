package modele;

import java.net.Socket;

import controler.Controle;
import outils.connexion.AsyncResponse;
import outils.connexion.Connection;

/**
 * Informations et méthodes communes aux jeux client et serveur
 *
 */
public abstract class Jeu {
	/**
	 * propriété de type Controle
	 */
	protected Controle control;

	/**
	 * Réception d'une connexion (pour communiquer avec un ordinateur distant)
	 */
	public abstract void connexion(Connection connection, Socket socket, AsyncResponse delegate) ;
	
	/**
	 * Réception d'une information provenant de l'ordinateur distant
	 */
	public abstract void reception(Connection connection, Object info) ;
	
	/**
	 * Déconnexion de l'ordinateur distant
	 */
	public abstract void deconnexion() ;
	
	/**
	 * Envoi d'une information vers un ordinateur distant
	 */
	public void envoi(Connection connection, Object info) {
		control.envoi(connection, info);
	}
	
}
