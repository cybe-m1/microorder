## Creneau

Les Créneaux possèdent tous un id unique, et son lié aux Trucks par une clé étrangère.

### Schéma de l'objet Créneau

```json
{
  "id_creneau": 1,
  "name": "NouveauCréneau",
  "description": "Description du créneau",
  "date": "date au format yyyy-MM-dd"
}
```

### Les routes de l'api pour les créneaux

Pour récupérer une liste de créneaux dans la base :</br>
method : **[GET]**</br>
url : `localhost:8081/api/creneaux`</br>

Pour récupérer un créneau dans la base :</br>
method : **[GET]**</br>
url : `localhost:8081/api/creneaux/{idDuCreneauxSouhaiter}`</br>

Pour récupérer un créneau dans la base à partir de son attribut date:</br>
method : **[POST]**</br>
url : `localhost:8081/api/creneaux/search`</br>
Body :</br>
```json
{
  "date": "date au format yyyy-MM-dd"
}
```

Modifier intégralement un créneau l'id est requis afin de savoir le quel modifier</br>
method : **[PUT]** </br>
url : `localhost:8081/api/creneaux`</br>
Body :</br>
```json
{
    "id_creneau": 1,
    "name": "NouveauCréneau",
    "description": "Description du créneau",
    "date": "date au format yyyy-MM-dd"
}
```

Pour ajouter un créneau dans la base :</br>
method : **[POST]**</br>
url : `localhost:8081/api/creneaux`</br>
Body:</br>
//Pas besoin de mettre d'id il est générer automatiquement</br>
```json
{
    "name": "Nom du créneau",
    "description": "Description du créneau",
    "date": "date au format yyyy-MM-dd"
}
```

Pour supprimer un créneau de la base :</br>
method : **[DELETE]**</br>
url : `localhost:8081/api/creneaux/{idDuCreneau}`</br>












