package classes;

import java.util.LinkedList;

public class RessourceHumaine extends Ressource {
	LinkedList<String> informations;
	
	public RessourceHumaine() {
		informations = new LinkedList<String>();
	}
	
	public void add(String element) {
		informations.add(element);
	}
}
