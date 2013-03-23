package tests;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import classes.Tache;

public class TacheTest {
	Tache tache;
	Calendar date;
	
	@Before
	public void initTache() {
		date.set(2013, Calendar.FEBRUARY, 23);
		Integer charge = new Integer(2);
		String objectifs = new String("objectifs");
				
		tache = new Tache(charge, objectifs, date, date, date, date, date, date);
	}
	
	@Test
	public void test() {
		
	}

}
