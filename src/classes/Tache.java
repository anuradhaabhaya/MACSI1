package classes;

import java.util.LinkedList;

public class Tache {
	LinkedList<String> informations;

	public Tache() {
		informations = new LinkedList<String>();
	}
	
	public void add(String element) {
		informations.add(element);
	}

	public String getIdTache() {
		String iD = null;
		try {
			iD = informations.getFirst();
		}
		catch (NullPointerException e) {
			System.out.println("Aucune information concernant la tache.");
		}
		return iD;
	}
}
