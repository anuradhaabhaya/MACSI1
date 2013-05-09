package classes;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

import enumerations.BaseDeDonnees;

public class TableauDeBord {
	private Connection connexion;
	private Projet projet;
	private LinkedList<Tache> tachesPrevues;
	private LinkedList<Tache> tachesEnCours;
	private LinkedList<Tache> tachesTerminees;
	private LinkedList<Jalon> jalons;
	private LinkedList<RessourceHumaine> ressHum;
	private LinkedList<RessourceMaterielle> ressMat;
	private LinkedList<RessourceLogicielle> ressLog;
	private String dFinInitiale;
	private String dFinFinale;
	private LinkedList<String> projetsValables;
	private LinkedList<String> sousProjets;
	private boolean contientSsProjets;
	private BDRequete req;
	private int avancementProjet;
	private HashMap<Integer, Integer> utilisationHum;
	private HashMap<Integer, Integer> utilisationLog;
	private HashMap<Integer, Integer> utilisationMat;

	public TableauDeBord(Projet projet) throws SQLException {
		req = new BDRequete(connexion);
		connexion = req.connexion();

		verifierValidite(projet);
		this.projet = projet;
		contientSsProjets = contientSousProjets(projet);

		projetsValables = new LinkedList<String>();
		sousProjets = new LinkedList<String>();
		tachesPrevues = new LinkedList<Tache>();
		tachesEnCours = new LinkedList<Tache>();
		tachesTerminees = new LinkedList<Tache>();
		jalons = new LinkedList<Jalon>();
		ressHum = new LinkedList<RessourceHumaine>();
		ressMat = new LinkedList<RessourceMaterielle>();
		ressLog = new LinkedList<RessourceLogicielle>();
		dFinInitiale = new String();
		dFinFinale = new String();
		avancementProjet = 0;
		utilisationHum = new HashMap<Integer, Integer>();
		utilisationLog = new HashMap<Integer, Integer>();
		utilisationMat = new HashMap<Integer, Integer>();
	}

	private boolean verifierValidite(Projet proj) throws SQLException {
		req.requete(BaseDeDonnees.VERIF_PROJET, "SELECT idprojet FROM projet;");

		projetsValables = req.getProjetsValables();

		if (projetsValables.contains(Integer.toString(proj.getIdProjet()))) {
			this.projet = proj;
			return true;
		}
		return false;
	}

	private boolean contientSousProjets(Projet proj) throws SQLException {
		try {
			/*
			 * req.requete(BaseDeDonnees.RECUP_SS_PROJETS,
			 * "SELECT idprojet FROM sousprojet WHERE idprojet = '" +
			 * projet.getIdProjet() + "'");
			 */
			req.requete(BaseDeDonnees.RECUP_SS_PROJETS,
					"SELECT DISTINCT idprojet FROM sousprojet;");

			sousProjets = req.getSousProjets();

			if (sousProjets.contains(Integer.toString(projet.getIdProjet()))) {
				return true;
			}
		} catch (NullPointerException e) {
		}
		return false;
	}

	private String concatTachesIds() {
		String idsTaches = new String();
		int i = 0;

		for (Tache tmp : tachesEnCours) {
			if (i == 0) {
				idsTaches = idsTaches + "'" + tmp.getIdTache() + "'";
				i = 1;
			} else {
				idsTaches = idsTaches + "," + "'" + tmp.getIdTache() + "'";
			}
		}

		return idsTaches;
	}

	private int pourcentageAvancement() {
		int tachesAvancees = tachesTerminees.size();
		int total = tachesAvancees + tachesEnCours.size()
				+ tachesPrevues.size();

		if (total == 0) {
			return 0;
		}

		return (tachesAvancees / total) * 100;
	}

	private void pourcentUtilisationRessHum() {
		for (RessourceHumaine ress : ressHum) {
			utilisationHum.put(new Integer(ress.informations.get(1)),
					new Integer(ress.informations.get(2)));
		}
	}

	private void pourcentUtilisationRessLog() {
		for (RessourceLogicielle ress : ressLog) {
			utilisationLog.put(new Integer(ress.informations.get(1)),
					new Integer(ress.informations.get(2)));
		}
	}

	private void pourcentUtilisationRessMat() {
		for (RessourceMaterielle ress : ressMat) {
			utilisationMat.put(new Integer(ress.informations.get(1)),
					new Integer(ress.informations.get(2)));
		}
	}

	public void recupererInfos() throws SQLException {
		/* Selectionne les taches correspondant au projet courant */
		try {
			if (contientSsProjets == false) {
				/*
				 * Pas de sous projets, on utilise donc les tables PhaseProjet
				 * et TachePhaseProjet
				 */
				req.requete(
						BaseDeDonnees.RECUP_TACHES_PREVUES,
						"SELECT t.* "
								+ "FROM tache t, tachesenattente tp, tachephaseprojet tpp, phaseprojet pp "
								+ "WHERE t.idTache = tp.idTache "
								+ "AND t.idTache = tpp.idTache "
								+ "AND tpp.idPhaseProjet = pp.idPhaseProjet "
								+ "AND pp.idProjet = '" + projet.getIdProjet()
								+ "';");
				tachesPrevues = req.getTachesPrevues();

				req.requete(
						BaseDeDonnees.RECUP_TACHES_EN_COURS,
						"SELECT t.* "
								+ "FROM tache t, tachesencours tec, tachephaseprojet tpp, phaseprojet pp "
								+ "WHERE t.idTache = tec.idTache "
								+ "AND t.idTache = tpp.idTache "
								+ "AND tpp.idPhaseProjet = pp.idPhaseProjet "
								+ "AND pp.idProjet = '" + projet.getIdProjet()
								+ "';");
				tachesEnCours = req.getTachesEnCours();

				req.requete(
						BaseDeDonnees.RECUP_TACHES_TERMINEES,
						"SELECT t.* "
								+ "FROM tache t, tachesterminees tt, tachephaseprojet tpp, phaseprojet pp "
								+ "WHERE t.idTache = tt.idTache "
								+ "AND t.idTache = tpp.idTache "
								+ "AND tpp.idPhaseProjet = pp.idPhaseProjet "
								+ "AND pp.idProjet = '" + projet.getIdProjet()
								+ "';");
				tachesTerminees = req.getTachesTerminees();

				req.requete(BaseDeDonnees.RECUP_JALONS, "SELECT j.* "
						+ "FROM jalonprojet jp, jalon j "
						+ "WHERE jp.idjalon = j.idjalon "
						+ "AND jp.idProjet = '" + projet.getIdProjet() + "';");
				jalons = req.getJalons();

			} else if (contientSsProjets == true) {
				/*
				 * Le projet contient des sous projets, on utilise donc les
				 * tables PhaseSousProjet et TachePhaseSousProjet
				 */
				req.requete(
						BaseDeDonnees.RECUP_TACHES_PREVUES,
						"SELECT t.* "
								+ "FROM tache t, tachesenattente tp, tachephasesousprojet tpp, phasesousprojet pp, sousprojet sp "
								+ "WHERE t.idTache = tp.idTache "
								+ "AND t.idTache = tpp.idTache "
								+ "AND tpp.idPhaseSousProjet = pp.idPhaseSousProjet "
								+ "AND pp.idSousProjet = sp.idSousProjet "
								+ "AND sp.idProjet = '" + projet.getIdProjet()
								+ "';");
				tachesPrevues = req.getTachesPrevues();

				req.requete(
						BaseDeDonnees.RECUP_TACHES_EN_COURS,
						"SELECT t.* "
								+ "FROM tache t, tachesencours tec, tachephasesousprojet tpp, phasesousprojet pp, sousprojet sp "
								+ "WHERE t.idTache = tec.idTache "
								+ "AND t.idTache = tpp.idTache "
								+ "AND tpp.idPhaseSousProjet = pp.idPhaseSousProjet "
								+ "AND pp.idSousProjet = sp.idSousProjet "
								+ "AND sp.idProjet = '" + projet.getIdProjet()
								+ "';");
				tachesEnCours = req.getTachesEnCours();

				req.requete(
						BaseDeDonnees.RECUP_TACHES_TERMINEES,
						"SELECT t.* "
								+ "FROM tache t, tachesterminees tt, tachephasesousprojet tpp, phasesousprojet pp, sousprojet sp "
								+ "WHERE t.idTache = tt.idTache "
								+ "AND t.idTache = tpp.idTache "
								+ "AND tpp.idPhaseSousProjet = pp.idPhaseSousProjet "
								+ "AND pp.idSousProjet = sp.idSousProjet "
								+ "AND sp.idProjet = '" + projet.getIdProjet()
								+ "';");
				tachesTerminees = req.getTachesTerminees();

				req.requete(BaseDeDonnees.RECUP_JALONS, "SELECT j.* "
						+ "FROM jalonsousprojet jp, jalon j "
						+ "WHERE jp.idjalon = j.idjalon "
						+ "AND jp.idProjet = '" + projet.getIdProjet() + "';");
				jalons = req.getJalons();
			}
			avancementProjet = pourcentageAvancement();

			if (contientSsProjets == true) {
				req.requete(
						BaseDeDonnees.RECUP_DATE_FIN_INITIALE,
						"SELECT max(t.datefinauplustard) "
								+ "FROM tache t, tachephasesousprojet tpp, phasesousprojet psp, sousprojet sp "
								+ "WHERE t.idTache = tpp.idTache "
								+ "AND tpp.idPhaseSousProjet = psp.idPhaseSousProjet "
								+ "AND psp.idSousProjet = sp.idSousProjet "
								+ "AND sp.idProjet = '" + projet.getIdProjet()
								+ "';");
			} else {
				req.requete(
						BaseDeDonnees.RECUP_DATE_FIN_INITIALE,
						"SELECT max(t.datefinauplustard) "
								+ "FROM tache t, tachephaseprojet tpp, phaseprojet psp "
								+ "WHERE t.idTache = tpp.idTache "
								+ "AND tpp.idPhaseProjet = psp.idPhaseProjet "
								+ "AND psp.idProjet = '" + projet.getIdProjet()
								+ "';");
			}
			dFinInitiale = req.getdFinInitiale();

			// À modifier
			if (dFinInitiale != null) {
				req.requete(BaseDeDonnees.RECUP_DATE_FIN_FINALE,
						"SELECT max(t.datefineffective) " + "FROM tache t "
								+ "WHERE t.datefinauplustard = '"
								+ dFinInitiale + "';");
				dFinFinale = req.getdFinFinale();
			}

			String TachesIds = concatTachesIds();

			if (!TachesIds.isEmpty()) {
				req.requete(BaseDeDonnees.RECUP_RESS_HUM, "SELECT h.* "
						+ "FROM resshumaine h, resshumaffectesurtache t "
						+ "WHERE t.idtache IN (" + TachesIds
						+ ") AND h.nss = t.nss;");
				ressHum = req.getRessHum();
				pourcentUtilisationRessHum();

				req.requete(BaseDeDonnees.RECUP_RESS_LOG, "SELECT l.* "
						+ "FROM resslogicielle l, resslogaffectesurtache t "
						+ "WHERE t.idtache IN (" + TachesIds
						+ ") AND l.idlogiciel = t.idlogiciel;");
				ressLog = req.getRessLog();
				pourcentUtilisationRessLog();

				req.requete(BaseDeDonnees.RECUP_RESS_MAT, "SELECT m.* "
						+ "FROM ressmaterielle m, ressmataffectesurtache t "
						+ "WHERE t.idtache IN (" + TachesIds
						+ ") AND m.idmateriel = t.idmateriel;");
				ressMat = req.getRessMat();
				pourcentUtilisationRessMat();
			} else {
				System.out.println("Ressources non affectees aux taches.");
			}
		} catch (NullPointerException e) {
			System.out.println("Ce projet n'existe pas dans la BDD.");
			// e.printStackTrace();
		}
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) throws SQLException {
		verifierValidite(projet);
		contientSsProjets = contientSousProjets(projet);
		this.projet = projet;
	}

	public void quitter() throws SQLException {
		req.deconnexion();
	}

	public Connection getConnexion() {
		return connexion;
	}

	public void setConnexion(Connection connexion) {
		this.connexion = connexion;
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

	public boolean isContientSsProjets() {
		return contientSsProjets;
	}

	public void setContientSsProjets(boolean contientSsProjets) {
		this.contientSsProjets = contientSsProjets;
	}

	public BDRequete getReq() {
		return req;
	}

	public void setReq(BDRequete req) {
		this.req = req;
	}

	public int getAvancementProjet() {
		return avancementProjet;
	}

	public void setAvancementProjet(int avancementProjet) {
		this.avancementProjet = avancementProjet;
	}

	public HashMap<Integer, Integer> getUtilisationHum() {
		return utilisationHum;
	}

	public void setUtilisationHum(HashMap<Integer, Integer> utilisationHum) {
		this.utilisationHum = utilisationHum;
	}

	public HashMap<Integer, Integer> getUtilisationLog() {
		return utilisationLog;
	}

	public void setUtilisationLog(HashMap<Integer, Integer> utilisationLog) {
		this.utilisationLog = utilisationLog;
	}

	public HashMap<Integer, Integer> getUtilisationMat() {
		return utilisationMat;
	}

	public void setUtilisationMat(HashMap<Integer, Integer> utilisationMat) {
		this.utilisationMat = utilisationMat;
	}
}
