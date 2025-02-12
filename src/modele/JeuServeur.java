package modele;

import java.util.ArrayList;
import java.util.Hashtable;
import controler.Controle;
import controler.Global;
import outils.connexion.Connection;

/**
 * Gestion du jeu côté serveur
 *
 */
public class JeuServeur extends Jeu implements Global {

	/**
	 * Controleur de Jeu redéfini ds la classe fille
	 */
	Controle controle;
	/**
	 * Collection de murs
	 */
	private ArrayList<Mur> lesMurs = new ArrayList<Mur>() ;
	/**
	 *  Dictionnaire de joueurs indexé sur leur objet de connexion
	 */
	private Hashtable<Connection, Joueur> lesJoueurs = new Hashtable<Connection, Joueur>() ;
	
	
	/**
	 * Constructeur
	 * @param controle instance du contrôleur pour les échanges
	 */
	public JeuServeur(Controle controle) {
		super.controle = controle;
	}
	
	/**
	 * Réception d'une connexion (pour communiquer avec un ordinateur distant)
	 */
	@Override
	public void connexion(Connection connection) {
		this.lesJoueurs.put(connection, new Joueur()) ;
	}

	@Override
	public void reception(Connection connection, Object info) {
		String[] infos=((String)info).split(STRINGSEPARE);
		String ordre = infos[0];
		switch(ordre) {
		case PSEUDO:
			controle.evenementJeuServeur(AJOUTPANELMURS, connection);
			String pseudo = infos[1];
			int num_perso = Integer.parseInt(infos[2]);
			this.lesJoueurs.get(connection).initPerso(pseudo, num_perso);
			break;
		
		}
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
		for(int k=0; k<NBMURS; k++) {
			lesMurs.add(new Mur());
			this.controle.evenementJeuServeur(AJOUTMUR, lesMurs.get(lesMurs.size()-1).getJLabel());
		}
	}
	
}
