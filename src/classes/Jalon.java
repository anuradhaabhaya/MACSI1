package classes;

import java.util.LinkedList;

public class Jalon {
	LinkedList<String> informations;
	
	public Jalon() {
		informations = new LinkedList<String>();
	}
	
	public void add(String element) {
		informations.add(element);
	}
}
