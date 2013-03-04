-- 04 mars 2013
-- 1. Creation des tables

macsi1=> CREATE TABLE Projet (
macsi1(> idProjet INTEGER PRIMARY KEY,
macsi1(> description VARCHAR(100));
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "projet_pkey" for table "projet"
CREATE TABLE

macsi1=> CREATE TABLE SousProjet (
macsi1(> idSousProjet INTEGER PRIMARY KEY,
macsi1(> description VARCHAR(100),
macsi1(> idProjet INTEGER,
macsi1(> idLot INTEGER);
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "sousprojet_pkey" for table "sousprojet"
CREATE TABLE

macsi1=> CREATE TABLE PhaseProjet (
macsi1(> idPhase INTEGER PRIMARY KEY,
macsi1(> idProjet INTEGER);
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "phaseprojet_pkey" for table "phaseprojet"
CREATE TABLE

macsi1=> CREATE TABLE PhaseSousProjet (
macsi1(> idPhase INTEGER PRIMARY KEY,
macsi1(> idSousProjet INTEGER);
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "phasesousprojet_pkey" for table "phasesousprojet"
CREATE TABLE

macsi1=> CREATE TABLE Lot (
macsi1(> idLot INTEGER PRIMARY KEY,
macsi1(> description VARCHAR(100));
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "lot_pkey" for table "lot"
CREATE TABLE

macsi1=> CREATE TABLE Jalon (
macsi1(> idJalon INTEGER PRIMARY KEY,
macsi1(> dateVerification DATE,
macsi1(> idProjet INTEGER,
macsi1(> idPhase INTEGER);
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "jalon_pkey" for table "jalon"
CREATE TABLE

macsi1=> CREATE TABLE Tache (
macsi1(> idTache INTEGER PRIMARY KEY,
macsi1(> dateDebEffective DATE,
macsi1(> dateDebAuPlusTard DATE,
macsi1(> dateDebAuPlusTot DATE,
macsi1(> dateFinEffective DATE,
macsi1(> dateFinAuPlusTard DATE,
macsi1(> dateFinAuPlusTot DATE,
macsi1(> charge NUMERIC(5, 2),
macsi1(> descriptionObjectifs VARCHAR(100),
macsi1(> idPhase INTEGER);
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "tache_pkey" for table "tache"
CREATE TABLE

macsi1=> CREATE TABLE TypeCompetence (
macsi1(> idCompetence INTEGER PRIMARY KEY,
macsi1(> nom VARCHAR(20));
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "typecompetence_pkey" for table "typecompetence"
CREATE TABLE

macsi1=> CREATE TABLE Livrable (
macsi1(> idLivrable INTEGER PRIMARY KEY,
macsi1(> type VARCHAR(20),
macsi1(> dateProduction DATE,
macsi1(> idTache INTEGER);
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "livrable_pkey" for table "livrable"
CREATE TABLE

-- Ajouter une cle primaire
macsi1=> CREATE TABLE RessHumaine (
macsi1(> NSS NUMERIC(15),
macsi1(> nom VARCHAR(30),
macsi1(> prenom VARCHAR(30),
macsi1(> cout NUMERIC(7,2));
CREATE TABLE
macsi1=> ALTER TABLE RessHumaine ADD CONSTRAINT resshumaine_pkey PRIMARY KEY (nss);
NOTICE:  ALTER TABLE / ADD PRIMARY KEY will create implicit index "resshumaine_pkey" for table "resshumaine"
ALTER TABLE

macsi1=> CREATE TABLE RessMaterielle (
macsi1(> idMateriel INTEGER PRIMARY KEY,
macsi1(> idTypeMateriel INTEGER);
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "ressmaterielle_pkey" for table "ressmaterielle"
CREATE TABLE

macsi1=> CREATE TABLE RessLogicielle (
macsi1(> idLogiciel INTEGER PRIMARY KEY,
macsi1(> nom VARCHAR(30),
macsi1(> cout NUMERIC(7,2));
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "resslogicielle_pkey" for table "resslogicielle"
CREATE TABLE

macsi1=> CREATE TABLE TypeMateriel (
macsi1(> idType INTEGER PRIMARY KEY,
macsi1(> description VARCHAR(100),
macsi1(> cout NUMERIC(7,2));
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "typemateriel_pkey" for table "typemateriel"
CREATE TABLE

-- Ajouter deux cles primaires
macsi1=> CREATE TABLE RessHumAffecteSurTache (
macsi1(> idTache INTEGER,
macsi1(> NSS NUMERIC(15),
macsi1(> taux INTEGER,
macsi1(> duree INTEGER,
macsi1(> CONSTRAINT idTache_NSS_pkey PRIMARY KEY (idTache, NSS));
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "idtache_nss_pkey" for table "resshumaffectesurtache"
CREATE TABLE

macsi1=> CREATE TABLE RessMatAffecteSurTache (
macsi1(> idTache INTEGER,
macsi1(> idMateriel INTEGER,
macsi1(> taux INTEGER, 
macsi1(> duree INTEGER,
macsi1(> CONSTRAINT idTache_idMateriel_pkey PRIMARY KEY (idTache, idMateriel));
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "idtache_idmateriel_pkey" for table "ressmataffectesurtache"
CREATE TABLE

macsi1=> CREATE TABLE RessLogAffecteSurTache (
macsi1(> idTache INTEGER,
macsi1(> idLogiciel INTEGER,
macsi1(> taux INTEGER,
macsi1(> duree INTEGER,
macsi1(> CONSTRAINT idTache_idLogiciel_pkey PRIMARY KEY (idTache, idLogiciel));
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "idtache_idlogiciel_pkey" for table "resslogaffectesurtache"
CREATE TABLE

macsi1=> CREATE TABLE RessMatRealiseSurTache (
idTache INTEGER,
NSS NUMERIC(15),
duree INTEGER,
CONSTRAINT idTache_NSS_2_pkey PRIMARY KEY (idTache, NSS));
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "idtache_nss_2_pkey" for table "ressmatrealisesurtache"
CREATE TABLE

-- Renommer une table
macsi1=> ALTER TABLE RessMatRealiseSurTache RENAME TO RessMatRealiseTache
macsi1-> ;
ALTER TABLE

macsi1=> ALTER TABLE RessMatRealiseTache RENAME TO RessHumRealiseTache;
ALTER TABLE

macsi1=> CREATE TABLE RessLogRealiseTache (
macsi1(> idTache INTEGER,
macsi1(> idLogiciel INTEGER,
macsi1(> duree INTEGER,
macsi1(> CONSTRAINT idTache_idLogiciel_2_pkey PRIMARY KEY (idTache, idLogiciel));
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "idtache_idlogiciel_2_pkey" for table "resslogrealisetache"
CREATE TABLE

macsi1=> CREATE TABLE RessMatRealiseTache (
macsi1(> idTache INTEGER,
macsi1(> idMateriel INTEGER,
macsi1(> duree INTEGER,
macsi1(> CONSTRAINT idTache_idMateriel_2_pkey PRIMARY KEY (idTache, idMateriel));
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "idtache_idmateriel_2_pkey" for table "ressmatrealisetache"
CREATE TABLE

macsi1=> CREATE TABLE TacheNecessiteTypeComp (
macsi1(> idTache INTEGER,
macsi1(> idCompetence INTEGER,
macsi1(> CONSTRAINT idTache_idCompetence_pkey PRIMARY KEY (idTache, idCompetence));
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "idtache_idcompetence_pkey" for table "tachenecessitetypecomp"
CREATE TABLE

macsi1=> CREATE TABLE RessHumPossedeCompetence (
macsi1(> NSS NUMERIC(15),
macsi1(> idcompetence INTEGER,
macsi1(> CONSTRAINT NSS_idCompetence_pkey PRIMARY KEY (NSS, idCompetence));
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "nss_idcompetence_pkey" for table "resshumpossedecompetence"
CREATE TABLE

macsi1=> CREATE TABLE DependanceTaches (
macsi1(> idTache1 INTEGER,
macsi1(> idTache2 INTEGER,
macsi1(> CONSTRAINT idTache1_idTache2_pkey PRIMARY KEY (idTache1, idTache2));
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "idtache1_idtache2_pkey" for table "dependancetaches"
CREATE TABLE

-- 2. Liste des tables
-- macsi1=> \dt

                 List of relations
 Schema |           Name           | Type  | Owner 
--------+--------------------------+-------+-------
 public | dependancetaches         | table | anou
 public | jalon                    | table | anou
 public | livrable                 | table | anou
 public | lot                      | table | anou
 public | phaseprojet              | table | anou
 public | phasesousprojet          | table | anou
 public | projet                   | table | anou
 public | resshumaffectesurtache   | table | anou
 public | resshumaine              | table | anou
 public | resshumpossedecompetence | table | anou
 public | resshumrealisetache      | table | anou
 public | resslogaffectesurtache   | table | anou
 public | resslogicielle           | table | anou
 public | resslogrealisetache      | table | anou
 public | ressmataffectesurtache   | table | anou
 public | ressmaterielle           | table | anou
 public | ressmatrealisetache      | table | anou
 public | sousprojet               | table | anou
 public | tache                    | table | anou
 public | tachenecessitetypecomp   | table | anou
 public | typecompetence           | table | anou
 public | typemateriel             | table | anou
(22 rows)

-- 3. Ajout des cles etrangeres

macsi1=> ALTER TABLE SousProjet
macsi1-> ADD CONSTRAINT idProjet_fkey
macsi1-> FOREIGN KEY (idProjet) REFERENCES Projet(idProjet);
ALTER TABLE
macsi1=> ALTER TABLE SousProjet
macsi1-> ADD CONSTRAINT idLot_fkey
macsi1-> FOREIGN KEY (idLot) REFERENCES Lot(idLot);
ALTER TABLE

macsi1=> ALTER TABLE PhaseProjet
macsi1-> ADD CONSTRAINT idProjet_fkey_2
macsi1-> FOREIGN KEY (idProjet) REFERENCES Projet(idProjet);
ALTER TABLE

macsi1=> ALTER TABLE PhaseSousProjet
macsi1-> ADD CONSTRAINT idSousProjet_fkey
macsi1-> FOREIGN KEY (idSousProjet) REFERENCES SousProjet(idSousProjet);
ALTER TABLE


macsi1=> ALTER TABLE Jalon      
ADD CONSTRAINT idProjet_fkey_3
FOREIGN KEY (idProjet) REFERENCES Projet(idProjet);
ALTER TABLE

-- Problème : idPhase correspond a la phase d'un projet ou d'un sous projet ? On choisit phase d'un projet, puis on renomme la table
macsi1=> ALTER TABLE Jalon
ADD CONSTRAINT idPhase_fkey
FOREIGN KEY (idPhase) REFERENCES PhaseProjet(idPhase);
ALTER TABLE
macsi1=> ALTER TABLE Jalon RENAME TO JalonProjet;
ALTER TABLE

-- Ajout d'une table
macsi1=> CREATE TABLE JalonSousProjet (
idJalon INTEGER PRIMARY KEY,
dateVerification DATE,
idProjet INTEGER,
idPhase INTEGER);
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "jalonsousprojet_pkey" for table "jalonsousprojet"
CREATE TABLE

macsi1=> ALTER TABLE JalonSousProjet
macsi1-> ADD CONSTRAINT idProjet_fkey_4
macsi1-> FOREIGN KEY (idProjet) REFERENCES Projet(idProjet);
ALTER TABLE

macsi1=> ALTER TABLE JalonSousProjet
macsi1-> ADD CONSTRAINT idPhase_fkey_2
macsi1-> FOREIGN KEY (idPhase) REFERENCES PhaseSousProjet(idPhase);
ALTER TABLE

-- Probleme ! Suppression de tables qui posent problème
NOTICE:  drop cascades to constraint idphase_fkey on table jalonprojet
DROP TABLE
macsi1=> drop table phasesousprojet cascade;
NOTICE:  drop cascades to constraint idphase_fkey_2 on table jalonsousprojet
DROP TABLE
macsi1=> drop table jalonprojet cascade;
DROP TABLE
macsi1=> drop table jalonsousprojet cascade;
DROP TABLE

-- Creation des bonnes tables
macsi1=> create table jalon (
idjalon integer primary key,
dateverification date,
idprojet integer,
idphase integer);
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "jalon_pkey" for table "jalon"
CREATE TABLE

macsi1=> create table phase (
macsi1(> idphase integer,
macsi1(> idprojet integer,
macsi1(> idsousprojet integer);
CREATE TABLE

-- Retour aux cles etrangeres

macsi1=> alter table phase
macsi1-> add constraint idprojet_fkey_4
macsi1-> foreign key (idprojet) references projet(idprojet);
ALTER TABLE

macsi1=> alter table phase
add constraint idsousprojet_fkey
foreign key (idsousprojet) references sousprojet(idsousprojet);
ALTER TABLE

macsi1=> alter table jalon
macsi1-> add constraint idprojet_fkey_5
macsi1-> foreign key (idprojet) references projet(idprojet);
ALTER TABLE

macsi1=> alter table phase
macsi1-> add constraint phase_pkey
macsi1-> primary key (idphase);
NOTICE:  ALTER TABLE / ADD PRIMARY KEY will create implicit index "phase_pkey" for table "phase"
ALTER TABLE

macsi1=> alter table jalon add constraint idphase_fkey foreign key (idphase) references phase(idphase);
ALTER TABLE

macsi1=> alter table tache add constraint idphase_fkey_2 foreign key (idphase) references phase(idphase);
ALTER TABLE

macsi1=> alter table livrable add constraint idtache_fkey foreign key (idtache) references tache(idtache);
ALTER TABLE

macsi1=> alter table ressmaterielle add constraint idtypemateriel_fkey foreign key (idtypemateriel) references typemateriel(idtype);
ALTER TABLE

macsi1=> alter table resshumaffectesurtache add constraint idtache_fkey_2 foreign key (idtache) references tache(idtache);
ALTER TABLE

macsi1=> alter table resshumaffectesurtache add constraint nss_fkey foreign key (nss) references resshumaine(nss);
ALTER TABLE

macsi1=> alter table ressmataffectesurtache add constraint idtache_fkey_3 foreign key (idtache) references tache(idtache);
ALTER TABLE

macsi1=> alter table ressmataffectesurtache add constraint idmateriel_fkey foreign key (idmateriel) references ressmaterielle(idmateriel);
ALTER TABLE

macsi1=> alter table resslogaffectesurtache add constraint idtache_fkey_4 foreign key (idtache) references tache(idtache);
ALTER TABLE

macsi1=> alter table resslogaffectesurtache add constraint idlogiciel_fkey foreign key (idlogiciel) references resslogicielle(idlogiciel);
ALTER TABLE

macsi1=> alter table resshumrealisetache add constraint idtache_fkey_5 foreign key (idtache) references tache(idtache);
ALTER TABLE

macsi1=> alter table resshumrealisetache add constraint nss_fkey_2 foreign key (nss) references resshumaine(nss);
ALTER TABLE

macsi1=> alter table ressmatrealisetache add constraint idtache_fkey_6 foreign key (idtache) references tache(idtache);
ALTER TABLE

macsi1=> alter table ressmatrealisetache add constraint idmateriel_fkey_2 foreign key (idmateriel) references ressmaterielle(idmateriel);
ALTER TABLE

macsi1=> alter table resslogrealisetache add constraint idtache_fkey_7 foreign key (idtache) references tache(idtache);
ALTER TABLE

macsi1=> alter table resslogrealisetache add constraint idlogiciel_fkey_2 foreign key (idlogiciel) references resslogicielle(idlogiciel);
ALTER TABLE

macsi1=> alter table tachenecessitetypecomp add constraint idtache_fkey_8 foreign key (idtache) references tache(idtache);
ALTER TABLE

macsi1=> alter table tachenecessitetypecomp add constraint idcompetence_fkey foreign key (idcompetence) references typecompetence(idcompetence);
ALTER TABLE

macsi1=> alter table resshumpossedecompetence add constraint nss_fkey_3 foreign key (nss) references resshumaine(nss);
ALTER TABLE

macsi1=> alter table resshumpossedecompetence add constraint idcompetence_fkey_2 foreign key (idcompetence) references typecompetence(idcompetence);
ALTER TABLE

macsi1=> alter table dependancetaches add constraint idtache_fkey_9 foreign key (idtache1) references tache(idtache);
ALTER TABLE

macsi1=> alter table dependancetaches add constraint idtache_fkey_10 foreign key (idtache2) references tache(idtache);
ALTER TABLE


