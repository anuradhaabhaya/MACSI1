package classes;

import java.util.LinkedList;

public class Projet {
	private LinkedList<SousProjet> listeSousProjet;
	private LinkedList<Phase> listePhase;
	private LinkedList<Jalon> listeJalon;

	public Projet() {
		listeJalon = new LinkedList<Jalon>();
	}

	public void ajouterSousProjet(SousProjet sousProjet)
			throws RuntimeException {
		if (!listePhase.isEmpty()) {
			throw new RuntimeException("Impossible de creer un sous projet si le projet possede des phases");
		}
		
		if (!listeSousProjet.isEmpty() && listeSousProjet.contains(sousProjet)) {
			throw new RuntimeException("La liste contient deja ce sous projet");
		}
		
		if (listeSousProjet.isEmpty()) {
			listeSousProjet = new LinkedList<SousProjet>();
		}
		
		if (!listeSousProjet.add(sousProjet)) {
			/* L'ajout du sous projet dans la liste, a echoue */
			throw new RuntimeException("Echec de l'ajout du sous projet");
		}
	}

	public void supprimerSousProjet(SousProjet sousProjet) throws RuntimeException {
		if (listeSousProjet.isEmpty()) {
			throw new RuntimeException("Suppression du sous projet impossible : la liste est vide");
		}
		
		if(!listeSousProjet.contains(sousProjet)) {
			throw new RuntimeException("Suppression du sous projet impossible : l'element n'est pas dans la liste");
		}
		
		if (!listeSousProjet.remove(sousProjet)) {
			/* La suppression du sous projet a echoue */
			throw new RuntimeException("Echec de la suppression du sous projet");
		}
	}

	/* Verifie que toutes les taches du projet sont terminees */
	public void cloturer() {

	}

	public void ajouterJalon(Jalon jalon) {

	}

	public void supprimerJalon(Jalon jalon) {

	}

	public void ajouterPhase(Phase phase) {

	}

	public void supprimerPhase(Phase phase) {

	}

	/* ? */
	public void sauvegarder() {

	}

}
