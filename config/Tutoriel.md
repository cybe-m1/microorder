# Tutoriel de config des services.

Ajoutez ces lignes au fichier de configuration yaml de votre service :


```yaml
spring:
  application:
    name: sample-app #Avec le nom de votre service (si ce n'est déjà fait)
  config:
    import: optional:config-app:http://localhost:7800
```

Dans le fichier application.yaml du service config, remplacez la variable d'env TOKEN_GIT par votre token GIT (sinon le service ne pourra pas récupérer la config sur le repo distant).

Le service config va aller chercher les fichiers config yaml sur ce [repo](https://github.com/cybe-m1/microorder_config)

Vous pouvez ajouter un fichier config sur celui-ci mais il faut qu'il ai le nom de votre application (ici sample-app).

N'oubliez pas de lancer le service config au préalable.

