package classes;

import java.util.LinkedList;

public class RessourceLogicielle extends Ressource {
	public LinkedList<String> informations;
	
	public RessourceLogicielle() {
		informations = new LinkedList<String>();
	}
	
	public void add(String element) {
		informations.add(element);
	}
}
