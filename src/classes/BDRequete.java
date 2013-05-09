package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import enumerations.BaseDeDonnees;

public class BDRequete {
	private Connection connexion;
	private LinkedList<String> projetsValables;
	private LinkedList<String> sousProjets;
	private LinkedList<Tache> tachesPrevues;
	private LinkedList<Tache> tachesEnCours;
	private LinkedList<Tache> tachesTerminees;
	private LinkedList<Jalon> jalons;
	private LinkedList<RessourceHumaine> ressHum;
	private LinkedList<RessourceMaterielle> ressMat;
	private LinkedList<RessourceLogicielle> ressLog;
	private String dFinInitiale;
	private String dFinFinale;
	
	public BDRequete(Connection connexion) {
		this.connexion = connexion;
		
		projetsValables = new LinkedList<String>();
		sousProjets = new LinkedList<String>();
		tachesPrevues = new LinkedList<Tache>();
		tachesEnCours = new LinkedList<Tache>();
		tachesTerminees = new LinkedList<Tache>();
		jalons = new LinkedList<Jalon>();
		ressHum = new LinkedList<RessourceHumaine>();
		ressMat = new LinkedList<RessourceMaterielle>();
		ressLog = new LinkedList<RessourceLogicielle>();
	}
	
	public void requete(BaseDeDonnees num, String req) throws SQLException {
		ResultSet resultats;
		String requete = req;
		Tache t;
		Jalon j;
		RessourceHumaine rh;
		RessourceLogicielle rl;
		RessourceMaterielle rm;

		System.out.println("REQUETE : " + requete);

		try {
			Statement stmt = connexion.createStatement();
			resultats = stmt.executeQuery(requete);

			ResultSetMetaData rsmd = resultats.getMetaData();
			int nbCols = rsmd.getColumnCount();

			/* Pour chaque ligne de resultat */
			while (resultats.next()) {
				if (num == BaseDeDonnees.VERIF_PROJET) {
					for (int i = 1; i <= nbCols; i++) {
						//System.out.println(resultats.getString(i));
						String str = new String (resultats.getString(i));
						projetsValables.add(str);
					}
				} else if (num == BaseDeDonnees.RECUP_SS_PROJETS) {
					for (int i = 1; i <= nbCols; i++) {
						sousProjets.add(resultats.getString(i));
					}
				} else if (num == BaseDeDonnees.RECUP_TACHES_PREVUES) {
					t = new Tache();
					/* Pour chaque colonne d'un resultat */
					for (int i = 1; i <= nbCols; i++) {
						/* Ajout des informations concernant la tache */
						t.add(resultats.getString(i));
					}
					/* Ajout de la tache dans la liste des taches */
					tachesPrevues.add(t);
				} else if (num == BaseDeDonnees.RECUP_TACHES_EN_COURS) {
					t = new Tache();
					/* Pour chaque colonne d'un resultat */
					for (int i = 1; i <= nbCols; i++) {
						/* Ajout des informations concernant la tache */
						t.add(resultats.getString(i));
					}
					/* Ajout de la tache dans la liste des taches */
					tachesEnCours.add(t);
				} else if (num == BaseDeDonnees.RECUP_TACHES_TERMINEES) {
					t = new Tache();
					/* Pour chaque colonne d'un resultat */
					for (int i = 1; i <= nbCols; i++) {
						/* Ajout des informations concernant la tache */
						t.add(resultats.getString(i));
					}
					/* Ajout de la tache dans la liste des taches */
					tachesTerminees.add(t);
				} else if (num == BaseDeDonnees.RECUP_JALONS) {
					j = new Jalon();
					for (int i = 1; i <= nbCols; i++) {
						j.add(resultats.getString(i));
					}
					jalons.add(j);
				} else if (num == BaseDeDonnees.RECUP_RESS_HUM) {
					rh = new RessourceHumaine();
					for (int i = 1; i <= nbCols; i++) {
						rh.add(resultats.getString(i));
					}
					ressHum.add(rh);
				} else if (num == BaseDeDonnees.RECUP_RESS_LOG) {
					rl = new RessourceLogicielle();
					for (int i = 1; i <= nbCols; i++) {
						rl.add(resultats.getString(i));
					}
					ressLog.add(rl);
				} else if (num == BaseDeDonnees.RECUP_RESS_MAT) {
					rm = new RessourceMaterielle();
					for (int i = 1; i <= nbCols; i++) {
						rm.add(resultats.getString(i));
					}
					ressMat.add(rm);
				} else if (num == BaseDeDonnees.RECUP_DATE_FIN_INITIALE) {
					if (nbCols == 1) {
						dFinInitiale = resultats.getString(1);
					}
				} else if (num == BaseDeDonnees.RECUP_DATE_FIN_FINALE) {
					if (nbCols == 1) {
						dFinFinale = resultats.getString(1);
					}
				}
			}

			resultats.close();
		} catch (SQLException e) {
			System.out.println("Requete impossible.");
			// e.printStackTrace();
		}
	}

	public Connection connexion() {
		System.out.println("-------- PostgreSQL "
				+ "JDBC Connexion ------------");

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Pensez a inclure votre PostgreSQL JDBC Driver "
					+ "dans votre library path.");
			//e.printStackTrace();
			return null;
		}

		try {
			connexion = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/macsi1", "groupemacsi1",
					"groupemacsi1");
		} catch (SQLException e) {
			System.out.println("Connexion impossible.");
			//e.printStackTrace();
			return null;
		}

		if (connexion != null) {
			System.out.println("Connexion reussie.");
		} else {
			System.out.println("Echec pour etablir la connexion.");
		}
		
		return connexion;
	}

	public Connection getConnexion() {
		return connexion;
	}

	public void setConnexion(Connection connexion) {
		this.connexion = connexion;
	}
	
	public void deconnexion() throws SQLException {
		try {
			connexion.close();
		} catch (NullPointerException e) {
			System.out.println("Vous n'etes pas connecte !");
		}
	}

	public LinkedList<String> getProjetsValables() {
		return projetsValables;
	}

	public void setProjetsValables(LinkedList<String> projetsValables) {
		this.projetsValables = projetsValables;
	}

	public LinkedList<String> getSousProjets() {
		return sousProjets;
	}

	public void setSousProjets(LinkedList<String> sousProjets) {
		this.sousProjets = sousProjets;
	}

	public LinkedList<Tache> getTachesPrevues() {
		return tachesPrevues;
	}

	public void setTachesPrevues(LinkedList<Tache> tachesPrevues) {
		this.tachesPrevues = tachesPrevues;
	}

	public LinkedList<Tache> getTachesEnCours() {
		return tachesEnCours;
	}

	public void setTachesEnCours(LinkedList<Tache> tachesEnCours) {
		this.tachesEnCours = tachesEnCours;
	}

	public LinkedList<Tache> getTachesTerminees() {
		return tachesTerminees;
	}

	public void setTachesTerminees(LinkedList<Tache> tachesTerminees) {
		this.tachesTerminees = tachesTerminees;
	}

	public LinkedList<Jalon> getJalons() {
		return jalons;
	}

	public void setJalons(LinkedList<Jalon> jalons) {
		this.jalons = jalons;
	}

	public LinkedList<RessourceHumaine> getRessHum() {
		return ressHum;
	}

	public void setRessHum(LinkedList<RessourceHumaine> ressHum) {
		this.ressHum = ressHum;
	}

	public LinkedList<RessourceMaterielle> getRessMat() {
		return ressMat;
	}

	public void setRessMat(LinkedList<RessourceMaterielle> ressMat) {
		this.ressMat = ressMat;
	}

	public LinkedList<RessourceLogicielle> getRessLog() {
		return ressLog;
	}

	public void setRessLog(LinkedList<RessourceLogicielle> ressLog) {
		this.ressLog = ressLog;
	}

	public String getdFinInitiale() {
		return dFinInitiale;
	}

	public void setdFinInitiale(String dFinInitiale) {
		this.dFinInitiale = dFinInitiale;
	}

	public String getdFinFinale() {
		return dFinFinale;
	}

	public void setdFinFinale(String dFinFinale) {
		this.dFinFinale = dFinFinale;
	}
	
	
}
