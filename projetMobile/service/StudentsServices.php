<?php
include_once  '../classes/Students.php';
include_once   '../connexion/Database.php';
include_once   '../dao/Students_Dao.php';

class StudentsServices implements Students_Dao {

    private $database;

    public function __construct() {
        $this->database = new Database();
    }

    /* ===================== CREATE ===================== */
    public function create($student) {
        $sql = "INSERT INTO etudiant (nom, prenom, ville, sexe)
                VALUES (:nom, :prenom, :ville, :sexe)";
        
        $statement = $this->database->getPdo()->prepare($sql);
        $statement->execute([
            ':nom' => $student->getLastName(),
            ':prenom' => $student->getFirstName(),
            ':ville' => $student->getCity(),
            ':sexe' => $student->getGender()
        ]);
    }

    /* ===================== DELETE ===================== */
    public function delete($student) {
        $sql = "DELETE FROM etudiant WHERE id = :id";
        
        $statement = $this->database->getPdo()->prepare($sql);
        $statement->execute([
            ':id' => $student->getId()
        ]);
    }

    /* ===================== UPDATE ===================== */
    public function update($student) {
        $sql = "UPDATE etudiant 
                SET nom = :nom, prenom = :prenom, ville = :ville, sexe = :sexe
                WHERE id = :id";

        $statement = $this->database->getPdo()->prepare($sql);
        $statement->execute([
            ':nom' => $student->getLastName(),
            ':prenom' => $student->getFirstName(),
            ':ville' => $student->getCity(),
            ':sexe' => $student->getGender(),
            ':id' => $student->getId()
        ]);
    }

    /* ===================== FIND ALL ===================== */
    public function findAll() {
        $sql = "SELECT * FROM etudiant";
        $query = $this->database->getPdo()->query($sql);
        return $query->fetchAll(PDO::FETCH_ASSOC);
    }

    /* ===================== FIND BY ID ===================== */
    public function findById($id) {
        $sql = "SELECT * FROM etudiant WHERE id = :id";
        
        $statement = $this->database->getPdo()->prepare($sql);
        $statement->execute([':id' => $id]);

        return $statement->fetch(PDO::FETCH_ASSOC);
    }

    /* ===================== API METHOD ===================== */
    public function findAllApi() {
        return $this->findAll();
    }
}
?>