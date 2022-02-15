# Java Spring Project : Easy food truck

## Offre d’emploi

 - Adrien Bassail
 - Henri Boulnois

Le tp à été réalisé en pair programming. 
Il permet de gérer un système d'offres d'emploi. Il y est possible de créer des offres d'emploi, et d'y affecter des candidats
Des compétences et loisirs peuvent être liés à un candidat, et une offre d'emploi peut demander certaines compétences. 

Le projet fonctionne sous forme d'API, et est fait en JAVA avec spring boot. 
Le projet est un projet maven. 

Des données d'exemple pour la base de données sont stockées dans le fichier "data.sql" dans le dossier ressources. 

L'authentification fonctionne avec basicAuth. Le nom d'utilisateur est "user", et le mot de passe est généré à chaque démarage de l'application dans la console JAVA. (elle est désactivable en retirant la dépendance maven). 

La console SQL est disponible sur : localhost:8080/console 
identifiant / mdp dans le fichier application.properties

  
## Components

|Emoji| Meaning |
|--|--|
|:white_check_mark: | GET |
|:orange_book:|POST|
|:red_circle:|DEL|
|:blue_book:|PUT|



### Candidat :busts_in_silhouette:

#### Candidat properties :

	    {
		    "nom": "Boulnois",
		    "prenom": "Henri",
		    "dateDeNaissance": "2000-04-10",
		    "email": "henri.boulnois@lacatholille.fr"
	    }

#### Requests :

|Request|Path Example|
|-|-|
|:white_check_mark: GetCandidat|localhost:8080/candidats/1|                                        
|:orange_book: PostCandidat | localhost:8080/candidats|
|:orange_book: AddLoisir|localhost:8080/candidats/1/addLoisir/2|
|:orange_book: AddCompetence|localhost:8080/candidats/1/addCompetence/2?niveauExpertise=1|


#### Rules :

 - Candidat needs to be between 18 and 67 years old to be registered.
 - The expertise level must be between 1 and 5

### Competence :memo:

#### Competence properties :

    {
	    "nom": "Excell",
	    "descriptif": "Je sait utiliser les cases du tableur"
    }

#### Requests :


|Request|Path Example|
|-|-|
|:white_check_mark: GetCompetence|localhost:8080/competences/3|                                        
|:white_check_mark: SearchCompetenceByName|localhost:8080/competences/searchByNom/E|
|:orange_book: PostCompetence | localhost:8080/competences|
|:blue_book: PutCompetence|localhost:8080/competences|
|:red_circle: DeleteCompetence|localhost:8080/competences/delete/4|

#### Rules :

 - The expertise level must be between 1 and 5

### Loisirs :art:

#### Loisirs properties :

    {
	    "nom": "Jeux vidéos",
	    "descriptif": "Je joue a des jeux multi comme LOL"
    }

#### Requests :

|Request|Path Example|
|-|-|
|:white_check_mark: GetLoisir|localhost:8080/loisirs/3|                                        
|:white_check_mark: SearchLoisirByName|localhost:8080/loisirs/searchByNom/Eq|
|:orange_book: PostLoisir | localhost:8080/loisirs|
|:blue_book: PutLoisir|localhost:8080/loisirs|
|:red_circle: DeleteLoisir|localhost:8080/loisirs/delete/3|

### Offre d’emploi :page_facing_up:

#### Offre d’emploi properties :

    {
	    "nom": "Responsable RH",
	    "descriptif": "Le but de la mission sera de gérer les ressources RH de l'entreprise, notamment de faire des entretiens préalables d'embauche.",
	    "dateDispo": "2022-01-10",
	    "actif": true
    }

#### Requests :

|Request|Path Example|
|-|-|
|:white_check_mark: GetOffreEmploi|localhost:8080/offresEmplois/1|                                        
|:white_check_mark: SearchOffreEmploiByName|localhost:8080/offresEmplois/searchByNom/D|
|:orange_book: PostOffreEmploi | localhost:8080/offresEmplois|
|:orange_book: AddCompetence|localhost:8080/offresEmplois/1/addCompetence/1|
|:blue_book: PutOffreEmploi|localhost:8080/offresEmplois|
|:red_circle: DeleteOffreEmploi|localhost:8080/offresEmplois/delete/1|
|:orange_book: Postuler|localhost:8080/offresEmplois/2/postuler/2|
|:white_check_mark: listeCandidats|localhost:8080/offresEmplois/2/listeCandidats|


#### Rules :

 - A competence must be requested or wished but not be both
 - A candidate can’t apply for an inactive offer
 - A candidate needs two competence to apply
