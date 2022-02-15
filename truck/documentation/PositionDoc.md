## Position

Les Positions possèdent tous un id unique, et son lié aux Trucks par une clé étrangère.

### Schéma de l'objet Position

```json
{
  "id_position": 1,
  "name": "Nom du position",
  "description": "Description du position",
  "longitude": "une valeur comprise entre 180 et -180",
  "latitude": "une valeur comprise entre 90 et -90"
}
```

### Les routes de l'api pour les positions

Pour récupérer une liste de positions dans la base :</br>
method : **[GET]**</br>
url : `localhost:8081/api/positions`</br>

Pour récupérer une position dans la base :</br>
method : **[GET]**</br>
url : `localhost:8081/api/positions/{idDeLaPositionSouhaiter}`</br>

Pour récupérer une position dans la base à partir de son attribut name(la recherche
partial est possible):</br>
method : **[GET]**</br>
url : `localhost:8081/api/positions/search/{NameRechercher}`</br>

Modifier intégralement une position l'id est requis afin de savoir le quel modifier</br>
method : **[PUT]** </br>
url : `localhost:8081/api/positions`</br>
Body:</br>
```json
{
    "id_position": 1,
    "name": "Nom du position",
    "description": "Description du position",
    "longitude": "une valeur comprise entre 180 et -180",
    "latitude": "une valeur comprise entre 90 et -90"
}
```

Pour ajouter une position dans la base :</br>
method : **[POST]**</br>
url : `localhost:8081/api/positions`</br>
Body :</br>
//Pas besoin de mettre d'id il est générer automatiquement</br>
```json
{
    "name": "Nom du position",
    "description": "Description du position",
    "longitude": "une valeur comprise entre 180 et -180",
    "latitude": "une valeur comprise entre 90 et -90"
}
```

Pour supprimer une position de la base :</br>
Régle : Une position ne peut être supprimer si un truc y va</br>
method : **[DELETE]**</br>
url : `localhost:8081/api/positions/{ideDeLaPosition}`</br>
