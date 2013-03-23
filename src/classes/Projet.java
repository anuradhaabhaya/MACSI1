package classes;

import java.util.LinkedList;

import exceptions.DejaDefiniException;
import exceptions.EchecException;
import exceptions.ExclusionException;
import exceptions.NonDefiniException;
import exceptions.NullException;

public class Projet {
	private LinkedList<SousProjet> listeSousProjet;
	private LinkedList<Phase> listePhase;
	private LinkedList<Jalon> listeJalon;

	public Projet() {
		listeJalon = new LinkedList<Jalon>();
	}

	public void ajouterSousProjet(SousProjet sousProjet)
			throws ExclusionException, DejaDefiniException, EchecException {
		if (listePhase != null) {
			throw new ExclusionException(
					"Impossible de creer un sous projet si le projet possede des phases");
		}

		if ((listeSousProjet != null) && !listeSousProjet.isEmpty()
				&& listeSousProjet.contains(sousProjet)) {
			throw new DejaDefiniException(
					"La liste contient deja ce sous projet");
		}

		if (listeSousProjet == null) {
			listeSousProjet = new LinkedList<SousProjet>();
		}

		if (!listeSousProjet.add(sousProjet)) {
			throw new EchecException("Echec de l'ajout du sous projet");
		}
	}

	public void supprimerSousProjet(SousProjet sousProjet)
			throws NullException, NonDefiniException, EchecException {
		if (listeSousProjet == null) {
			throw new NullException(
					"Suppression du sous projet impossible : la liste n'existe pas");
		}

		if (listeSousProjet.isEmpty()) {
			throw new NonDefiniException(
					"Suppression du sous projet impossible : la liste est vide");
		}

		if (!listeSousProjet.contains(sousProjet)) {
			throw new NonDefiniException(
					"Suppression du sous projet impossible : l'element n'est pas dans la liste");
		}

		if (!listeSousProjet.remove(sousProjet)) {
			throw new EchecException("Echec de la suppression du sous projet");
		}
	}

	/* Verifie que toutes les taches du projet sont terminees */
	public void cloturer() {

	}

	public void ajouterJalon(Jalon jalon) throws DejaDefiniException, EchecException {
		if (listeJalon.contains(jalon)) {
			throw new DejaDefiniException("Ce jalon existe deja");
		}

		if (!listeJalon.add(jalon)) {
			throw new EchecException("Echec de l'ajout du jalon");
		}
	}

	public void supprimerJalon(Jalon jalon) throws NonDefiniException, EchecException {
		if (listeJalon.isEmpty()) {
			throw new NonDefiniException(
					"Suppression du jalon impossible : la liste est vide");
		}

		if (!listeJalon.contains(jalon)) {
			throw new NonDefiniException(
					"Suppression du jalon impossible : l'element n'est pas dans la liste");
		}

		if (!listeJalon.remove(jalon)) {
			throw new EchecException("Echec de la suppression du jalon");
		}
	}

	public void ajouterPhase(Phase phase) throws ExclusionException,
			DejaDefiniException, EchecException {
		if (listeSousProjet != null) {
			throw new ExclusionException(
					"Impossible de creer une phase si le projet possede des sous projets");
		}

		if ((listePhase != null) && !listePhase.isEmpty()
				&& listePhase.contains(phase)) {
			throw new DejaDefiniException("Cette phase existe deja");
		}

		if (listePhase == null) {
			listePhase = new LinkedList<Phase>();
		}

		if (!listePhase.add(phase)) {
			throw new EchecException("Echec de l'ajout de la phase");
		}
	}

	public void supprimerPhase(Phase phase) throws NullException, NonDefiniException, EchecException {
		if (listePhase == null) {
			throw new NullException(
					"Suppression de la phase impossible : la liste n'existe pas");
		}

		if (listePhase.isEmpty()) {
			throw new NonDefiniException(
					"Suppression de la phase impossible : la liste est vide");
		}

		if (!listePhase.contains(phase)) {
			throw new NonDefiniException(
					"Suppression de la phase impossible : l'element n'est pas dans la liste");
		}

		if (!listePhase.remove(phase)) {
			throw new EchecException("Echec de la suppression de la phase");
		}
	}

	/* ? */
	public void sauvegarder() {

	}

}
