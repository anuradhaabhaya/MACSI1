package tests;

import org.junit.Before;
import org.junit.Test;

import classes.Lot;
import classes.SousProjet;
import exceptions.DejaDefiniException;
import exceptions.ExclusionException;
import exceptions.NonDefiniException;

public class LotTest {
	Lot lot;
	SousProjet sousProjet;

	@Before
	public void initLot() {
		lot = new Lot("Lot");
		sousProjet = new SousProjet("sousProjet");
	}
	
	@Test
	public void ajourSuppression() throws DejaDefiniException, NonDefiniException {		
		lot.ajouterSousProjet(sousProjet);
		lot.supprimerSousProjet(sousProjet);
	}
	
	@Test(expected = DejaDefiniException.class)
	public void ajoutMemeSousProjet() throws ExclusionException,
			DejaDefiniException {
		lot.ajouterSousProjet(sousProjet);
		lot.ajouterSousProjet(sousProjet);
	}

	@Test(expected = NonDefiniException.class)
	public void suppressionSousProjetListeVide() throws NonDefiniException {
		lot.supprimerSousProjet(sousProjet);
	}

	@Test(expected = NonDefiniException.class)
	public void suppressionSousProjetNonPresentDansListe()
			throws ExclusionException, DejaDefiniException, NonDefiniException {
		SousProjet ssP1 = new SousProjet("SSP1");
		SousProjet ssP2 = new SousProjet("SSP2");
		SousProjet ssP3 = new SousProjet("SSP3");

		lot.ajouterSousProjet(ssP1);
		lot.ajouterSousProjet(ssP2);

		/* Suppression de ssP3 qui n'a pas ete ajoute a la liste */
		lot.supprimerSousProjet(ssP3);
	}

}
