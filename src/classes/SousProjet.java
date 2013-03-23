package classes;

import java.util.LinkedList;

import exceptions.DejaDefiniException;
import exceptions.NonDefiniException;

public class SousProjet {
	private LinkedList<Phase> listePhases;
	private String description;
	
	public SousProjet(String str) {
		listePhases = new LinkedList<Phase>();
		description = str;
	}

	public void ajouterPhase(Phase phase) throws DejaDefiniException {
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

	public LinkedList<Phase> getListePhases() {
		return listePhases;
	}

	public void setListePhases(LinkedList<Phase> listePhases) {
		this.listePhases = listePhases;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "SousProjet [listePhases=" + listePhases + ", description="
				+ description + "]";
	}
}
