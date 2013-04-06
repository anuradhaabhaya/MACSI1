package classes;

import java.util.LinkedList;

public class RessourceMaterielle extends Ressource {
	LinkedList<String> informations;
	
	public RessourceMaterielle() {
		informations = new LinkedList<String>();
	}
	
	public void add(String element) {
		informations.add(element);
	}
}
