package modele;

import java.net.Socket;

import controler.Controle;
import outils.connexion.AsyncResponse;
import outils.connexion.Connection;

/**
 * Gestion du jeu côté client
 *
 */
public class JeuClient extends Jeu {
	/**
	 * Controleur de Jeu redéfini ds la classe fille
	 */
	public Controle controle;
	/**
	 * Connection avec le serveur
	 */
	public Connection connectionServeur;
	/**
	 * Constructreur
	 */
	public JeuClient(Controle controle) {
	}
	
	/**
	 * Réception d'une connexion (pour communiquer avec un ordinateur distant)
	 */
	@Override
	public void connexion(Connection connection, Socket socket, AsyncResponse delegate) {
		Connection connectionServeur = new Connection(socket, delegate);
	}

	@Override
	public void reception(Connection connection, Object info) {
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers le serveur
	 * fais appel une fois à l'envoi dans la classe Jeu
	 */
	public void envoi(Connection connectionServeur, Object info) {
	}

}
