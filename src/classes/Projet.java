package classes;

import java.util.LinkedList;

import exceptions.DejaDefiniException;
import exceptions.EchecException;
import exceptions.ExclusionException;
import exceptions.NonDefiniException;

public class Projet {
	private LinkedList<SousProjet> listeSousProjets;
	private LinkedList<Phase> listePhases;
	private LinkedList<Jalon> listeJalons;

	public Projet() {
		listeSousProjets = new LinkedList<SousProjet>();
		listePhases = new LinkedList<Phase>();
		listeJalons = new LinkedList<Jalon>();
	}

	public void ajouterSousProjet(SousProjet sousProjet)
			throws ExclusionException, DejaDefiniException, EchecException {
		if (!listePhases.isEmpty()) {
			throw new ExclusionException(
					"Impossible de creer un sous projet si le projet possede des phases");
		}

		if (!listeSousProjets.isEmpty()
				&& listeSousProjets.contains(sousProjet)) {
			throw new DejaDefiniException(
					"La liste contient deja ce sous projet");
		}

		if (!listeSousProjets.add(sousProjet)) {
			throw new EchecException("Echec de l'ajout du sous projet");
		}
	}

	public void supprimerSousProjet(SousProjet sousProjet)
			throws NonDefiniException, EchecException {
		if (listeSousProjets.isEmpty()) {
			throw new NonDefiniException(
					"Suppression du sous projet impossible : la liste est vide");
		}

		if (!listeSousProjets.contains(sousProjet)) {
			throw new NonDefiniException(
					"Suppression du sous projet impossible : l'element n'est pas dans la liste");
		}

		if (!listeSousProjets.remove(sousProjet)) {
			throw new EchecException("Echec de la suppression du sous projet");
		}
	}

	/* Verifie que toutes les taches du projet sont terminees */
	public void cloturer() {

	}

	public void ajouterJalon(Jalon jalon) throws DejaDefiniException,
			EchecException {
		if (listeJalons.contains(jalon)) {
			throw new DejaDefiniException("Ce jalon existe deja");
		}

		if (!listeJalons.add(jalon)) {
			throw new EchecException("Echec de l'ajout du jalon");
		}
	}

	public void supprimerJalon(Jalon jalon) throws NonDefiniException,
			EchecException {
		if (listeJalons.isEmpty()) {
			throw new NonDefiniException(
					"Suppression du jalon impossible : la liste est vide");
		}

		if (!listeJalons.contains(jalon)) {
			throw new NonDefiniException(
					"Suppression du jalon impossible : l'element n'est pas dans la liste");
		}

		if (!listeJalons.remove(jalon)) {
			throw new EchecException("Echec de la suppression du jalon");
		}
	}

	public void ajouterPhase(Phase phase) throws ExclusionException,
			DejaDefiniException, EchecException {
		if (!listeSousProjets.isEmpty()) {
			throw new ExclusionException(
					"Impossible de creer une phase si le projet possede des sous projets");
		}

		if (!listePhases.isEmpty() && listePhases.contains(phase)) {
			throw new DejaDefiniException("Cette phase existe deja");
		}

		if (!listePhases.add(phase)) {
			throw new EchecException("Echec de l'ajout de la phase");
		}
	}

	public void supprimerPhase(Phase phase) throws NonDefiniException,
			EchecException {

		if (listePhases.isEmpty()) {
			throw new NonDefiniException(
					"Suppression de la phase impossible : la liste est vide");
		}

		if (!listePhases.contains(phase)) {
			throw new NonDefiniException(
					"Suppression de la phase impossible : l'element n'est pas dans la liste");
		}

		if (!listePhases.remove(phase)) {
			throw new EchecException("Echec de la suppression de la phase");
		}
	}

	/* ? */
	public void sauvegarder() {

	}

}
