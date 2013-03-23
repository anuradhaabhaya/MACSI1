package tests;

import org.junit.Before;
import org.junit.Test;

import classes.Jalon;
import classes.Phase;
import classes.Projet;
import classes.SousProjet;
import exceptions.DejaDefiniException;
import exceptions.ExclusionException;
import exceptions.NonDefiniException;

public class ProjetTest {
	Projet projet;
	SousProjet sousProjet;
	Jalon jalon;
	Phase phase;

	@Before
	public void initProjet() {
		projet = new Projet("ProjetTest");
		sousProjet = new SousProjet("SousProjet");
		jalon = new Jalon();
		phase = new Phase();
	}

	@Test
	public void ajouterSupprimerSousProjet() throws ExclusionException,
			DejaDefiniException, NonDefiniException {
		projet.ajouterSousProjet(sousProjet);
		projet.supprimerSousProjet(sousProjet);
	}

	@Test
	public void ajouterSupprimerPhase() throws ExclusionException,
			DejaDefiniException, NonDefiniException {
		projet.ajouterPhase(phase);
		projet.supprimerPhase(phase);
	}

	@Test
	public void ajouterSupprimerJalon() throws DejaDefiniException,
			NonDefiniException {
		projet.ajouterJalon(jalon);
		projet.supprimerJalon(jalon);
	}

	@Test(expected = ExclusionException.class)
	public void ajoutSousProjetPuisPhase() throws ExclusionException,
			DejaDefiniException {
		projet.ajouterSousProjet(sousProjet);
		projet.ajouterPhase(phase);
	}

	@Test(expected = ExclusionException.class)
	public void ajoutPhasePuisSousProjet() throws ExclusionException,
			DejaDefiniException {
		projet.ajouterPhase(phase);
		projet.ajouterSousProjet(sousProjet);
	}

	@Test(expected = DejaDefiniException.class)
	public void ajoutMemeSousProjet() throws ExclusionException,
			DejaDefiniException {
		projet.ajouterSousProjet(sousProjet);
		projet.ajouterSousProjet(sousProjet);
	}

	@Test(expected = NonDefiniException.class)
	public void suppressionSousProjetListeVide() throws NonDefiniException {
		projet.supprimerSousProjet(sousProjet);
	}

	@Test(expected = NonDefiniException.class)
	public void suppressionSousProjetNonPresentDansListe()
			throws ExclusionException, DejaDefiniException, NonDefiniException {
		SousProjet ssP1 = new SousProjet("SSP1");
		SousProjet ssP2 = new SousProjet("SSP2");
		SousProjet ssP3 = new SousProjet("SSP3");

		projet.ajouterSousProjet(ssP1);
		projet.ajouterSousProjet(ssP2);

		/* Suppression de ssP3 qui n'a pas ete ajoute a la liste */
		projet.supprimerSousProjet(ssP3);
	}

	@Test(expected = DejaDefiniException.class)
	public void ajoutMemePhase() throws ExclusionException, DejaDefiniException {
		projet.ajouterPhase(phase);
		projet.ajouterPhase(phase);
	}

	@Test(expected = NonDefiniException.class)
	public void suppressionPhaseListeVide() throws NonDefiniException {
		projet.supprimerPhase(phase);
	}

	@Test(expected = NonDefiniException.class)
	public void suppressionPhaseNonPresenteDansListe()
			throws ExclusionException, DejaDefiniException, NonDefiniException {
		Phase p1 = new Phase();
		Phase p2 = new Phase();
		Phase p3 = new Phase();

		projet.ajouterPhase(p1);
		projet.ajouterPhase(p2);

		/* Suppression de p3 qui n'a pas ete ajoute a la liste */
		projet.supprimerPhase(p3);
	}

	@Test(expected = DejaDefiniException.class)
	public void ajoutMemeJalon() throws DejaDefiniException {
		projet.ajouterJalon(jalon);
		projet.ajouterJalon(jalon);
	}

	@Test(expected = NonDefiniException.class)
	public void suppressionJalonListeVide() throws NonDefiniException {
		projet.supprimerJalon(jalon);
	}

	@Test(expected = NonDefiniException.class)
	public void suppressionJalonNonPresentDansListe()
			throws DejaDefiniException, NonDefiniException {
		Jalon j1 = new Jalon();
		Jalon j2 = new Jalon();
		Jalon j3 = new Jalon();

		projet.ajouterJalon(j1);
		projet.ajouterJalon(j2);

		/* Suppression de j3 qui n'a pas ete ajoute a la liste */
		projet.supprimerJalon(j3);
	}

	@Test
	public void cloturerProjet() {
		projet.cloturer();
		// fail("Not yet implemented");
	}

	@Test
	public void sauvegarderProjet() {
		projet.sauvegarder();
		// fail("Not yet implemented");
	}
}
