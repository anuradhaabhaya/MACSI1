package classes;

import java.util.LinkedList;

import exceptions.DejaDefiniException;
import exceptions.ExclusionException;
import exceptions.NonDefiniException;

public class Projet {
	private LinkedList<SousProjet> listeSousProjets;
	private LinkedList<Phase> listePhases;
	private LinkedList<Jalon> listeJalons;
	private String description;

	public Projet(String str) {
		listeSousProjets = new LinkedList<SousProjet>();
		listePhases = new LinkedList<Phase>();
		listeJalons = new LinkedList<Jalon>();
		description = str;
	}

	public void ajouterSousProjet(SousProjet sousProjet)
			throws ExclusionException, DejaDefiniException {
		if (!listePhases.isEmpty()) {
			throw new ExclusionException(
					"Impossible de creer un sous projet si le projet possede des phases");
		}

		if (listeSousProjets.contains(sousProjet)) {
			throw new DejaDefiniException(
					"La liste contient deja ce sous projet");
		}

		listeSousProjets.add(sousProjet);
	}

	public void supprimerSousProjet(SousProjet sousProjet)
			throws NonDefiniException {
		if (listeSousProjets.isEmpty()) {
			throw new NonDefiniException(
					"Suppression du sous projet impossible : la liste est vide");
		}

		if (!listeSousProjets.contains(sousProjet)) {
			throw new NonDefiniException(
					"Suppression du sous projet impossible : l'element n'est pas dans la liste");
		}

		listeSousProjets.remove(sousProjet);
	}

	public void ajouterPhase(Phase phase) throws ExclusionException,
			DejaDefiniException {
		if (!listeSousProjets.isEmpty()) {
			throw new ExclusionException(
					"Impossible de creer une phase si le projet possede des sous projets");
		}

		if (listePhases.contains(phase)) {
			throw new DejaDefiniException("Cette phase existe deja");
		}

		listePhases.add(phase);
	}

	public void supprimerPhase(Phase phase) throws NonDefiniException {
		if (listePhases.isEmpty()) {
			throw new NonDefiniException(
					"Suppression de la phase impossible : la liste est vide");
		}

		if (!listePhases.contains(phase)) {
			throw new NonDefiniException(
					"Suppression de la phase impossible : l'element n'est pas dans la liste");
		}

		listePhases.remove(phase);
	}

	public void ajouterJalon(Jalon jalon) throws DejaDefiniException {
		if (listeJalons.contains(jalon)) {
			throw new DejaDefiniException("Ce jalon existe deja");
		}

		listeJalons.add(jalon);
	}

	public void supprimerJalon(Jalon jalon) throws NonDefiniException {
		if (listeJalons.isEmpty()) {
			throw new NonDefiniException(
					"Suppression du jalon impossible : la liste est vide");
		}

		if (!listeJalons.contains(jalon)) {
			throw new NonDefiniException(
					"Suppression du jalon impossible : l'element n'est pas dans la liste");
		}

		listeJalons.remove(jalon);
	}

	/* Verifie que toutes les taches du projet sont terminees */
	public void cloturer() {

	}

	/* ? */
	public void sauvegarder() {

	}

	public LinkedList<SousProjet> getListeSousProjets() {
		return listeSousProjets;
	}

	public void setListeSousProjets(LinkedList<SousProjet> listeSousProjets) {
		this.listeSousProjets = listeSousProjets;
	}

	public LinkedList<Phase> getListePhases() {
		return listePhases;
	}

	public void setListePhases(LinkedList<Phase> listePhases) {
		this.listePhases = listePhases;
	}

	public LinkedList<Jalon> getListeJalons() {
		return listeJalons;
	}

	public void setListeJalons(LinkedList<Jalon> listeJalons) {
		this.listeJalons = listeJalons;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Projet [description=" + description + ", listeSousProjets=" + listeSousProjets
				+ ", listePhases=" + listePhases + ", listeJalons="
				+ listeJalons +  "]";
	}
}
