package controler;

import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;
import outils.connexion.AsyncResponse;
import outils.connexion.ClientSocket;
import outils.connexion.Connection;
import outils.connexion.ServeurSocket;
import vue.Arene;
import vue.ChoixJoueur;
import vue.EntreeJeu;
import vue.ChoixJoueur;
import javax.swing.JPanel;

/**
 * Contrôleur et point d'entrée de l'applicaton 
 * @author emds
 *
 */
public class Controle implements AsyncResponse, Global {
	/**
	 * frame EntreeJeu
	 */
	private EntreeJeu frmEntreeJeu ;
	/**
	 * frame Arene
	 */
	private Arene frmArene;
	/**
	 * frame ChoixJoueur
	 */
	private ChoixJoueur frmChoixJoueur;
	/**
	 *  instance du jeu (JeuServeur ou JeuClient)
	 */
	private Jeu leJeu;

	/**
	 * Méthode de démarrage
	 * @param args non utilisé
	 */
	public static void main(String[] args) {
		new Controle();
	}

	
	/**
	 * Constructeur
	 */
	private Controle() {
		this.frmEntreeJeu = new EntreeJeu(this) ;
		this.frmEntreeJeu.setVisible(true);
	}
	
	/**
	 * Demande provenant de la vue EntreeJeu
	 * @param info information à traiter
	 */
	public void evenementEntreeJeu(String info) {
		if(info.equals(SERVEUR)) {			
			new ServeurSocket(this, PORT);
			this.leJeu = new JeuServeur(this);
			this.frmEntreeJeu.dispose();			
			this.frmArene = new Arene();
			((JeuServeur)this.leJeu).constructionMurs();
			this.frmArene.setVisible(true);
		} else {
			new ClientSocket(this, info, PORT);
		}
		
	}
	
	/**
	 * Informations provenant de la vue ChoixJoueur
	 * @param pseudo le pseudo du joueur
	 * @param numPerso le numéro du personnage choisi par le joueur
	 */
	public void evenementChoixJoueur(String pseudo, int num_perso) {
		this.frmChoixJoueur.dispose();
		this.frmArene.setVisible(true);
		this.leJeu= new JeuClient(this);
		((JeuClient)leJeu).envoi(PSEUDO+STRINGSEPARE+pseudo+STRINGSEPARE+num_perso);
	}
	/**
	 * Informations provenant de JeuServeur pour ajouter des murs
	 * @param ordre de JeuServeur
	 * @param info que la méthode va exécuter selon ordre
	 */
	public void evenementJeuServeur (String ordre, Object info) {
		switch(ordre) {
		case AJOUTMUR :
			frmArene.ajoutMurs(info);
			break;
		case AJOUTPANELMURS :
			this.leJeu.envoi((Connection)info, this.frmArene.getJpnMurs());
			}
	}
	
	/**
	 * Demande provenant de JeuClient
	 * @param ordre ordre à exécuter
	 * @param info information à traiter
	 */
	public void evenementJeuClient(String ordre, Object info) {
		switch(ordre) {
		case AJOUTPANELMURS :
			this.frmArene.setJpnMurs((JPanel)info);
			break;
			}
		}
	
	/**
	 * Envoi d'informations vers l'ordinateur distant
	 * @param connection objet de connexion pour l'envoi vers l'ordinateur distant
	 * @param info information à envoyer
	 */
	public void envoi (Connection connection, Object info) {
		connection.envoi(info);	
	}
	
	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch(ordre) {
		case CONNEXION :
			if(!(this.leJeu instanceof JeuServeur)) {
				this.leJeu=new JeuClient(this);
				this.leJeu.connexion(connection);
				this.frmEntreeJeu.dispose();
				this.frmArene = new Arene();
				this.frmChoixJoueur = new ChoixJoueur(this);
				this.frmChoixJoueur.setVisible(true);
			}else {
				this.leJeu.connexion(connection);
			}
			break;
		case RECEPTION :
			this.leJeu.reception(connection, info);
			break;
		case DECONNEXION :
			break;
		}		
	}
	
	
}