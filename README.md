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
nom:	Ahmed
prenom:	Youssef
ville:	Marrakech
sexe:	Homme

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

**Partie 3 — Application Android (Volley + Gson)**


