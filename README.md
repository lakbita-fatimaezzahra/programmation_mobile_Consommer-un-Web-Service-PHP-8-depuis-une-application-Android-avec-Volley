# programmation_mobile_Consommer-un-Web-Service-PHP-8-depuis-une-application-Android-avec-Volley

**Partie 1 — Création de la base de données MySQL**

*Créer la base de données : ecole*

<img width="1124" height="644" alt="image" src="https://github.com/user-attachments/assets/f6642189-fa01-4625-85d7-e45d16148241" />

*Créer la table:  Etudiant *

<img width="1484" height="434" alt="image" src="https://github.com/user-attachments/assets/a219a0a1-4771-4139-ba34-777e9b333693" />

*Ajouter des enregistrements tests :*

<img width="1380" height="214" alt="image" src="https://github.com/user-attachments/assets/46c3d03f-8dd9-45e2-b08c-849c810ce207" />

**Partie 2 — Développement du Web Service PHP 8**

Étape 1 : Structure du projet

<img width="534" height="609" alt="image" src="https://github.com/user-attachments/assets/ade3a6ec-b4bf-46b7-b3bb-decc2e9c04c1" />

Étape 2 :Tester les Web Services avec *Postman*

Avant d’utiliser une application Android, il faut vérifier que :

              **Le serveur PHP fonctionne
 
              La connexion à la base de données fonctionne
 
              Les Web Services retournent du JSON correct
 
              Il n’y a pas d’erreurs PHP**

Et donc on teste le backend séparément du mobile.

   1. Installation du *Postman*

 **# Postman** est une application complète dédiée au test d’API.
 
 <img width="1907" height="989" alt="image" src="https://github.com/user-attachments/assets/1c56fba8-ddc8-474a-bd66-2adc8470ef9e" />

 
  2. Test du service d’ajout d’un étudiant(**Tester createEtudiant.php (POST)**)

 Dans Postman :

1️ Nouvelle requête

2️ Méthode : POST

3️ URL :

**http://localhost/projetmobile/ws/CreateStudent.php**

4 Ajouter les données

Clique sur :

Body → x-www-form-urlencoded

Ajouter :

KEY:	VALUE

<img width="1346" height="680" alt="image" src="https://github.com/user-attachments/assets/5847ac21-9f4b-46c7-9530-0105341cfeb5" />

Puis clique sur Send

5 Résultat attendu

      #L’étudiant est ajouté

      #la liste mise à jour en JSON
      
     ** vue JSON**
     
   <img width="1303" height="355" alt="image" src="https://github.com/user-attachments/assets/8287f6e5-fc2f-42b9-ac28-a255a5117a92" />

     ** vue DATABASE**
     
   <img width="1099" height="437" alt="image" src="https://github.com/user-attachments/assets/74e421fa-292c-437a-99f2-0076f900ef82" />

  3. Test du service de lecture des étudiants (**Tester loadEtudiant.php (GET)**)
     
     Dans Postman :

1️ Clique sur New → HTTP Request

2️ Méthode : GET

3️ URL :

**http://localhost/projetmobile/ws/GetStudent.php**

<img width="1325" height="281" alt="image" src="https://github.com/user-attachments/assets/e18877ed-314e-48d5-a1b0-7bc3820090f3" />

4️ Clique sur Send

5 Résultat attendu

      recevoir quelque chose comme :
      
 <img width="1347" height="840" alt="image" src="https://github.com/user-attachments/assets/8f7b4506-87ba-42a9-80ca-04411cd7e395" />

**Partie 3 —Développement de l’Application Android**

         #Technologies utilisées : Volley + Gson
         
L’application permet :

➤ L’ajout d’un étudiant

➤ La récupération des données depuis le serveur

➤ L’affichage des résultats JSON

➤ La communication via HTTP (Volley)

➤ La conversion JSON → Objet Java (Gson)

      1.Création du projet Android

      1.1 Environnement

IDE : Android Studio

Type : Empty Activity

Nom du projet : My Application

Langage : Java

API Minimum : 26 ou supérieur

Après création, le projet est compilé afin de vérifier l’absence d’erreurs initiales.

     2.Configuration réseau
     
     2.1 Permission Internet

Dans le fichier AndroidManifest.xml, ajout de la permission :

<uses-permission android:name="android.permission.INTERNET" />

<img width="1165" height="392" alt="image" src="https://github.com/user-attachments/assets/60aaebfa-6386-42b7-a52d-98758cf7a258" />

Cette permission est obligatoire pour permettre à l’application d’envoyer des requêtes HTTP vers le serveur local.

    3.Ajout des dépendances

   3.1 Dans le fichier build.gradle (Module: app) :

dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'com.android.volley:volley:1.2.1'
    implementation 'com.google.code.gson:gson:2.10.1'
    testImplementation 'junit:junit:4.13.2'
}

  3.2 Rôle des bibliothèques :

Volley:	Gestion des requêtes HTTP

Gson:	Conversion JSON ↔ Objet Java


 4.Développement de l’activité AddStudent
 
4.1 Définition des styles

**Ajout de styles personnalisés dans styles.xml pour harmoniser l’interface.**

<img width="1619" height="666" alt="image" src="https://github.com/user-attachments/assets/5c963f56-8427-4b83-97a8-8915d9209649" />

4.2 Conception de l’interface utilisateur

Création du layout activity_add_Student.xml comprenant :

EditText pour Nom et Prénom

Spinner pour Ville

<img width="1196" height="376" alt="image" src="https://github.com/user-attachments/assets/0e2fe520-c898-4aa6-8d1b-25d7f0910c63" />

RadioGroup pour Sexe

Bouton Ajouter

Interface simple, ergonomique et adaptée aux tests API.

<img width="644" height="835" alt="image" src="https://github.com/user-attachments/assets/dd5e2db2-b41f-4c66-91c7-2d74a14fe7a8" />

5.Implémentation logique — AddStudent.java
  5.1 Initialisation

Récupération des composants UI

Initialisation de RequestQueue (Volley)

Ajout d’un OnClickListener
-------
<img width="1093" height="597" alt="image" src="https://github.com/user-attachments/assets/51eeefe0-4705-4092-a0eb-244dcba976fa" />

5.2 Envoi des données au serveur

Méthode envoyerEtudiant() :

Création d’une requête POST

Envoi des paramètres : nom, prenom, ville, sexe

Réception de la réponse JSON

Conversion avec Gson

Affichage dans Logcat

5.3 Fonctionnement interne

Étape 1 : Envoi HTTP

Volley envoie une requête POST vers :

http://10.0.2.2/projetMobile/ws/createStudent.php

**10.0.2.2 représente localhost depuis l’émulateur Android.**

Étape 2: Réponse JSON

Le serveur PHP retourne :

[
 {"id":"1","nom":"LACHGAR","prenom":"Mohamed","ville":"Rabat","sexe":"homme"}......
]

Étape 3 : Conversion avec Gson

Type type = new TypeToken<Collection<Etudiant>>(){}.getType();
Collection<Etudiant> etudiants = new Gson().fromJson(response, type);

JSON → Objet Java → Manipulable dans l’application

6. Classe Etudiant (Model)

Création du package beans contenant la classe Etudiant.

Elle représente le modèle correspondant à la table etudiant en base de données.

Rôle :

Stocker les données

Permettre la conversion Gson

Faciliter l’affichage

7. Sécurité réseau Android 9+
   Depuis Android 9, le trafic HTTP non sécurisé est bloqué.

7.1 Création de network_security_config.xml

Autorisation du trafic vers :10.0.2.2

<img width="946" height="297" alt="image" src="https://github.com/user-attachments/assets/ecec8778-70a1-4564-aff0-89d9a40665bd" />

7.2 Configuration dans le Manifest

android:networkSecurityConfig="@xml/network_security_config"

android:usesCleartextTraffic="true"

Permet les tests locaux sans HTTPS.

8.Test et Validation

8.1 Conditions préalables

Apache démarré

MySQL actif

Web Service fonctionnel

8.2 Procédure

Lancer l’émulateur

Saisir un étudiant

<img width="420" height="817" alt="image" src="https://github.com/user-attachments/assets/4f0f2356-247f-4fb3-9b3d-d734486f62f3" />

Cliquer sur Ajouter

Observer Logcat
======

8.3 Résultat attendu
===
Affichage :

<img width="1708" height="182" alt="image" src="https://github.com/user-attachments/assets/55c618f7-2261-4f0b-8d50-f3c55e60b0b0" />


Cela confirme :
====
✔ Communication Android → PHP
✔ Insertion en base
✔ Retour JSON
✔ Conversion Gson réussie

<img width="1007" height="216" alt="image" src="https://github.com/user-attachments/assets/8af7e6e9-5352-4c80-8526-c53d51c3099d" />

9.Extension du projet
    Objectif
  ====

Créer une activité affichant la liste complète des étudiants.

Fonctionnalités attendues
====
Utilisation de ListView 

Chargement dynamique via Volley

Popup pour modification

Suppression avec confirmation

Rafraîchissement automatique après action
