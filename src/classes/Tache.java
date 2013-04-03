package classes;

import java.util.LinkedList;

public class Tache {
	LinkedList<String> tache;

	public Tache() {
		tache = new LinkedList<String>();
	}
	
	public void add(String element) {
		tache.add(element);
	}

}
