## Trucks

Les trucks possèdent tous un id unique, et son lié aux autres objets par des clés étrangères.

### Schéma de l'objet Truck

```json
{
  "id_truck": 1,
  "name": "Nom du truck",
  "description": "Description du truck",
  "id_creneau": 1,
  "id_position": 1
}
```

### Les routes de l'api pour les trucks

Pour récupérer une liste des trucks dans la base.</br>
method : **[GET]**</br>
url : `localhost:8081/api/trucks</br>`

Pour récupérer un truck dans la base.</br>
method : **[GET]**</br>
url : `localhost:8081/api/trucks/{idDuTruckSouhaiter}`</br>

Pour affecter à un truck une position.</br>
Régle : Trois truck ne peuvent être au même endroit</br>
method : **[PUT]** </br>
url : `localhost:8081/api/trucks/{idDuTruck}/position/{idDeLaPosition}`</br>

Pour affecter à un truck une creneau.</br>
method : **[PUT]** </br>
url : `localhost:8081/api/trucks/{idDuTruck}/creneau/{idDuCreneau}`</br>

Modifie intégralement un truck, l'id est requis afin de savoir le quel modifier</br>
Régle : Trois trucks ne peuvent être au même endroit</br>
method : **[PUT]** </br>
url : `localhost:8081/api/trucks`</br>
Body:</br>
```json
{
    "id_truck": 1,
    "name": "Nom du truck",
    "description": "Description du truck",
    "id_creneau": 1,
    "id_position": 1
}
```

Pour ajouter un truck dans la base.</br>
Régle : Trois trucks ne peuvent être au même endroit</br>
method : **[POST]**</br>
url : `localhost:8081/api/trucks`</br>
Body:</br>
//Pas besoin de mettre d'id il est générer automatiquement</br>
```json
{
    "name": "Nom du truck",
    "description": "Description du truck",
    "id_creneau": 1,
    "id_position": 1
}
```

Pour supprimer un truck de la base :</br>
method : **[DELETE]**</br>
url : `localhost:8081/api/trucks/{idDuTruck}`</br>
