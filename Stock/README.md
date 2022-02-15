
# ESAY FOOD TRUCK

gestion des stocks

## RUN 

```
  mvn clean install
  ```

import bdd :

```
src/main/resources/static/bdd/easy_food_truck_2021-12-17.sql
  ```

## Swagger 

###  - swagger UI 
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

###  - Postman

importer le fichier easy-food-truck.yml dans postman ou Insomnia


### REGLE METIER: 

- chaque ingredient contient le nombre de produit dans le lot par exemple une boite d'oeuf est composé de 6 oeufs. 
- chaque nom d'ingredient, menu, product est unique. 
 chaque ingredient posséde une unitée (g,l,unitary)

- pour le Replenishment, si l'ingredient posséde une unité, c'est que la quantité du prpduit est un multiple de 'division '
sinon si c'est unitary et que ça ne tombe pas juste, on rajoute 1 à la quantité 
par exemple: une boite d'oeuf est vendu par 6, s'il en faut 7, on retourne 2
