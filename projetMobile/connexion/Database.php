<?php
class Database {
    private $pdoConnection;

    public function __construct() {
        try {
            $this->pdoConnection = new PDO(
                "mysql:host=localhost;dbname=ecole;charset=utf8",
                "root",
                ""
            );
            $this->pdoConnection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch (PDOException $exception) {
            die('Erreur : ' . $exception->getMessage());
        }
    }

    public function getPdo() {
        return $this->pdoConnection;
    }
}
?>