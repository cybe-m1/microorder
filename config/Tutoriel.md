# Tutoriel de config des services.

Ajoutez ces lignes à votre fichier de configuration yaml :


```yaml
spring:
  application:
    name: sample-app #Avec le nom de votre service (si ce n'est déjà fait)
  config:
    import: optional:config-app:http://localhost:7800
```
Le service config va aller chercher les fichiers config yaml sur ce [repo](https://github.com/cybe-m1/microorder_config)

Vous pouvez ajouter un fichier config sur celui-ci mais il faut qu'il ai le nom de votre application (ici sample-app).

N'oubliez pas de lancer le service config au préalable.

