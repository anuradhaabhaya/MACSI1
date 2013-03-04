PROJET MACSI1

INI2 - 2012-2013

Groupe: 
- Anuradha
- Antoine
- Amel
- Amin

-- 04 mars 2013 --
- Creation de la base de données avec PostgreSQL.
- Ajout d'un exemple en Java pour pouvoir se connecter à la BDD.
- 'CreationBDlog.sql' est l'historique de ce que j'ai fait.

- A faire :
Sous ECLIPSE : Creer un nouveau projet Java nomme "MACSI1", puis importer tout ce qu'il y a sur github, dedans.

- Pour importer la base de donnees : 
1. Installez PostgreSQL. Puis, placez vous dans le dossier du projet.
("$" signifie que vous lancez ces commandes en console)
2. $ sudo -i -u postgres
3. $ psql
4. $ CREATE USER groupemacsi1
5. $ ALTER ROLE groupemacsi1 WITH CREATEDB
6. $ CREATE DATABASE macsi1 OWNER groupemacsi1
7. $ ALTER USER groupemacsi1 WITH ENCRYPTED PASSWORD 'groupemacsi1';
8. $ \q
9. $ psql macsi1
10. $ \i macsi1BD
11. $ \q
12. $ exit
13. $ psql macsi1
14. $ \dt
=> la liste des tables doit s'afficher.
(\q pour quitter)
