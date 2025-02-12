package modele;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controler.Global;
/**
 * Gestion des murs
 *
 */
public class Mur extends Objet implements Global {
	
	/**
	 * position X de l'objet
	 */
	private Integer posX ;
	/**
	 * position Y de l'objet
	 */
	private Integer posY ;
	
	
	/**
	 * Constructeur: crée un mur (position aléatoire, image)
	 */
	public Mur() {
		// calcul position aléatoire du mur
		posX= Math.round((int)Math.random()*(LARGEURARENE - LARGEURMUR)); 
		posY= Math.round((int)Math.random()*(HAUTEURARENE - HAUTEURMUR));		
		JLabel jLabel = new JLabel();
		// caractéristiques du mur (position, image)
		jLabel.setBounds(posX, posY, LARGEURMUR, HAUTEURMUR);
		URL resource = getClass().getClassLoader().getResource(MUR);
		jLabel.setIcon(new ImageIcon(resource));		
		
		
		
	}
	
}
