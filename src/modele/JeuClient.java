package modele;

import javax.swing.JPanel;
import controler.Controle;
import controler.Global;
import outils.connexion.Connection;

/**
 * Gestion du jeu côté client
 *
 */
public class JeuClient extends Jeu implements Global {
	/**
	 * Controleur de Jeu redéfini ds la classe fille
	 */
	public Controle controle;
	/**
	 * objet de connexion pour communiquer avec le serveur
	 */
	public Connection connection;
	/**
	 * Constructreur
	 */
	public JeuClient(Controle controle) {
		super.controle=controle;
	}
	
	/**
	 * Réception d'une connexion (pour communiquer avec un ordinateur distant)
	 */
	@Override
	public void connexion(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void reception(Connection connection, Object info) {
		if(info instanceof JPanel) {
			// arrivée du panel des murs
			this.controle.evenementJeuClient(AJOUTPANELMURS, info);
		}
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers le serveur
	 * fais appel une fois à l'envoi dans la classe Jeu
	 * @param info information à envoyer au serveur
	 */
	public void envoi(Object info) {
		super.envoi(this.connection, info);
	}

}
