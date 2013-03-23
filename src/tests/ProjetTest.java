package tests;

import org.junit.Before;
import org.junit.Test;

import classes.Jalon;
import classes.Phase;
import classes.Projet;
import classes.SousProjet;
import exceptions.DejaDefiniException;
import exceptions.EchecException;
import exceptions.ExclusionException;
import exceptions.NonDefiniException;
import exceptions.NullException;

public class ProjetTest {
	Projet projet;
	SousProjet sousProjet;
	Jalon jalon;
	Phase phase;

	@Before
	public void initProjet() {
		projet = new Projet();
		sousProjet = new SousProjet();
		jalon = new Jalon();
		phase = new Phase();
	}

	@Test
	public void ajouterSupprimerSousProjet() throws ExclusionException,
			DejaDefiniException, EchecException, NullException,
			NonDefiniException {
		projet.ajouterSousProjet(sousProjet);
		projet.supprimerSousProjet(sousProjet);
	}

	@Test
	public void ajouterSupprimerPhase() throws ExclusionException,
			DejaDefiniException, EchecException, NullException,
			NonDefiniException {
		projet.ajouterPhase(phase);
		projet.supprimerPhase(phase);
	}

	@Test
	public void ajouterSupprimerJalon() throws DejaDefiniException,
			EchecException, NonDefiniException {
		projet.ajouterJalon(jalon);
		projet.supprimerJalon(jalon);
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

	@Test(expected = ExclusionException.class)
	public void ajoutSousProjetEtPhase() throws ExclusionException,
			DejaDefiniException, EchecException {
		projet.ajouterSousProjet(sousProjet);
		projet.ajouterPhase(phase);
	}

	@Test(expected = ExclusionException.class)
	public void ajoutPhaseEtSousProjet() throws ExclusionException,
			DejaDefiniException, EchecException {
		projet.ajouterPhase(phase);
		projet.ajouterSousProjet(sousProjet);
	}

	@Test(expected = NullException.class)
	public void suppressionSousProjetSansCreation() throws NullException,
			NonDefiniException, EchecException {
		projet.supprimerSousProjet(sousProjet);
	}
}
