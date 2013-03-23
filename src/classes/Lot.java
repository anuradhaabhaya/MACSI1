package classes;

import java.util.LinkedList;

import exceptions.DejaDefiniException;
import exceptions.NonDefiniException;

public class Lot {
	private LinkedList<SousProjet> listeSousProjets;
	private String description;
	
	public Lot(String str) {
		listeSousProjets = new LinkedList<SousProjet>();
		description = str;
	}
	
	public void ajouterSousProjet(SousProjet sousProjet)
			throws DejaDefiniException {
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

	public LinkedList<SousProjet> getListeSousProjets() {
		return listeSousProjets;
	}

	public void setListeSousProjets(LinkedList<SousProjet> listeSousProjets) {
		this.listeSousProjets = listeSousProjets;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Lot [listeSousProjets=" + listeSousProjets + ", description="
				+ description + "]";
	}
}
