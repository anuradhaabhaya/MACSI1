package tests;

import classes.SousProjet;
import classes.Phase;

import org.junit.Before;
import org.junit.Test;

import exceptions.DejaDefiniException;
import exceptions.ExclusionException;
import exceptions.NonDefiniException;

public class SousProjetTest {
	SousProjet sousProjet;
	Phase phase;

	@Before
	public void initSousProjet() {
		sousProjet = new SousProjet("SousProjetTest");
		phase = new Phase();
	}
	
	@Test
	public void ajoutSuppression() throws DejaDefiniException, NonDefiniException {
		sousProjet.ajouterPhase(phase);
		sousProjet.supprimerPhase(phase);
	}
	
	@Test(expected = DejaDefiniException.class)
	public void ajoutMemePhase() throws ExclusionException, DejaDefiniException {
		sousProjet.ajouterPhase(phase);
		sousProjet.ajouterPhase(phase);
	}

	@Test(expected = NonDefiniException.class)
	public void suppressionPhaseListeVide() throws NonDefiniException {
		sousProjet.supprimerPhase(phase);
	}

	@Test(expected = NonDefiniException.class)
	public void suppressionPhaseNonPresenteDansListe()
			throws ExclusionException, DejaDefiniException, NonDefiniException {
		Phase p1 = new Phase();
		Phase p2 = new Phase();
		Phase p3 = new Phase();

		sousProjet.ajouterPhase(p1);
		sousProjet.ajouterPhase(p2);

		/* Suppression de p3 qui n'a pas ete ajoute a la liste */
		sousProjet.supprimerPhase(p3);
	}
}
