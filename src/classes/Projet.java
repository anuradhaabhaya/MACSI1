package classes;

import java.util.LinkedList;


public class Projet {
	private int idProjet;
	public LinkedList<String> informations;
	
	public Projet() {
		informations = new LinkedList<String>();
	}
	
	public void add(String element) {
		//String elmt = new String(element);
		//informations.add(elmt);
		informations.add(element);
	}
	
	public int getIdProjet() {
		return idProjet;
	}
	
	public void setIdProjet(int idProjet) {
		this.idProjet = idProjet;
	}

	public LinkedList<String> getInformations() {
		return informations;
	}

	public void setInformations(LinkedList<String> informations) {
		this.informations = informations;
	}
}
