package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public abstract class ChoixProjet {
	
	public static LinkedList<Projet> getListeProjets() throws SQLException {
		LinkedList<Projet> liste = null;
		Connection connexion = null;
		ResultSet resultats;
		String requete = new String("SELECT * FROM projet;");

		/*
		 * Connexion a la base de donnees
		 */

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Pensez a inclure votre PostgreSQL JDBC Driver dans votre library path.");
			return null;
		}

		try {
			connexion = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/macsi1", "groupemacsi1",
					"groupemacsi1");
		} catch (SQLException e) {
			System.out.println("Connexion impossible.");
			return null;
		}

		if (connexion != null) {
			System.out.println("Connexion reussie.");

			/*
			 * Requete
			 */

			liste = new LinkedList<Projet>();

			Statement stmt = connexion.createStatement();
			resultats = stmt.executeQuery(requete);

			ResultSetMetaData rsmd = resultats.getMetaData();
			int nbCols = rsmd.getColumnCount();

			while (resultats.next()) {
				Projet p = new Projet();
				/* Met l'ID du projet a jour */
				p.setIdProjet(Integer.parseInt(resultats.getString(1)));
				for (int i = 1; i <= nbCols; i++) {
					/* Recuperer ID + description du projet */
					p.add(resultats.getString(i));
				}
				liste.add(p);
			}

		} else {
			System.out.println("Echec pour etablir la connexion.");
		}

		/*
		 * Deconnexion
		 */
		
		try {
			connexion.close();
		} catch (NullPointerException e) {
			System.out.println("Vous n'etes pas connecte !");
		}

		return liste;
	}
}
