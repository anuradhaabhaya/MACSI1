package tests;

import org.junit.Test;

import classes.Jalon;
import classes.Phase;
import classes.Projet;
import classes.SousProjet;

public class ProjetTest {
	Projet projet;
	SousProjet sousProjet;
	Jalon jalon;
	Phase phase;
	
	//fail("Not yet implemented");
	
	@Test
	public void initProjet() {
		projet = new Projet();
		sousProjet = new SousProjet();
		jalon = new Jalon();
		phase = new Phase();
		
		projet.ajouterSousProjet(sousProjet);
		projet.supprimerSousProjet(sousProjet);
		projet.cloturer();
		projet.ajouterJalon(jalon);
		projet.supprimerJalon(jalon);
		projet.ajouterPhase(phase);
		projet.supprimerPhase(phase);
		projet.sauvegarder();
	}
	
	@Test (expected = RuntimeException.class) 
	public void ajoutSousProjetEtPhase() {
		projet.ajouterSousProjet(sousProjet);
		projet.ajouterPhase(phase);
	}
	
	@Test (expected = RuntimeException.class) 
	public void ajoutPhaseEtSousProjet() {
		projet.ajouterPhase(phase);
		projet.ajouterSousProjet(sousProjet);
	}
	
}
