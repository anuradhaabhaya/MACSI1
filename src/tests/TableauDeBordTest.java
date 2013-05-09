package tests;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import classes.Projet;
import classes.TableauDeBord;

public class TableauDeBordTest {
	Projet p;
	TableauDeBord board;
	
	@Before
	public void initialisation() throws SQLException {
		p = new Projet();
		p.setIdProjet(1);
		
		board = new TableauDeBord(p);
		board.recupererInfos();
	}

	@Test
	public void initTableauDeBord() throws SQLException {
		//board.recupererInfos();		
	}

}
