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
		if (listePhase != null) {
			throw new RuntimeException(
					"Impossible de creer un sous projet si le projet possede des phases");
		}

		if ((listeSousProjet != null) && !listeSousProjet.isEmpty() && listeSousProjet.contains(sousProjet)) {
			throw new RuntimeException("La liste contient deja ce sous projet");
		}

		if (listeSousProjet == null) {
			listeSousProjet = new LinkedList<SousProjet>();
		}

		if (!listeSousProjet.add(sousProjet)) {
			throw new RuntimeException("Echec de l'ajout du sous projet");
		}
	}

	public void supprimerSousProjet(SousProjet sousProjet)
			throws RuntimeException {
		if (listeSousProjet == null) {
			throw new RuntimeException(
					"Suppression du sous projet impossible : la liste n'existe pas");
		}

		if (listeSousProjet.isEmpty()) {
			throw new RuntimeException(
					"Suppression du sous projet impossible : la liste est vide");
		}

		if (!listeSousProjet.contains(sousProjet)) {
			throw new RuntimeException(
					"Suppression du sous projet impossible : l'element n'est pas dans la liste");
		}

		if (!listeSousProjet.remove(sousProjet)) {
			throw new RuntimeException("Echec de la suppression du sous projet");
		}
	}

	/* Verifie que toutes les taches du projet sont terminees */
	public void cloturer() {

	}

	public void ajouterJalon(Jalon jalon) throws RuntimeException {
		if (listeJalon.contains(jalon)) {
			throw new RuntimeException("Ce jalon existe deja");
		}

		if (!listeJalon.add(jalon)) {
			throw new RuntimeException("Echec de l'ajout du jalon");
		}
	}

	public void supprimerJalon(Jalon jalon) throws RuntimeException {
		if (listeJalon.isEmpty()) {
			throw new RuntimeException(
					"Impossible de supprimer le jalon : la liste est vide");
		}

		if (!listeJalon.contains(jalon)) {
			throw new RuntimeException(
					"Impossible de supprimer le jalon : l'element n'est pas dans la liste");
		}

		if (!listeJalon.remove(jalon)) {
			throw new RuntimeException("Echec de la suppression du jalon");
		}
	}

	public void ajouterPhase(Phase phase) throws RuntimeException {
		if (listeSousProjet != null) {
			throw new RuntimeException(
					"Impossible de creer une phase si le projet possede des sous projets");
		}
		
		if ((listePhase != null) && !listePhase.isEmpty() && listePhase.contains(phase)) {
			throw new RuntimeException("Cette phase existe deja");
		}
		
		if (listePhase == null) {
			listePhase = new LinkedList<Phase>();
		}
		
		if (!listePhase.add(phase)) {
			throw new RuntimeException("Echec de l'ajout de la phase");
		}
	}

	public void supprimerPhase(Phase phase) {
		if (listePhase == null) {
			throw new RuntimeException(
					"Suppression de la phase impossible : la liste n'existe pas");
		}

		if (listePhase.isEmpty()) {
			throw new RuntimeException(
					"Suppression de la phase impossible : la liste est vide");
		}

		if (!listePhase.contains(phase)) {
			throw new RuntimeException(
					"Suppression de la phase impossible : l'element n'est pas dans la liste");
		}

		if (!listePhase.remove(phase)) {
			throw new RuntimeException("Echec de la suppression de la phase");
		}
	}

	/* ? */
	public void sauvegarder() {

	}

}
