<?php
class Students {
    private $studentId;
    private $lastName;
    private $firstName;
    private $city;
    private $gender;

    public function __construct($studentId, $lastName, $firstName, $city, $gender) {
        $this->studentId = $studentId;
        $this->lastName = $lastName;
        $this->firstName = $firstName;
        $this->city = $city;
        $this->gender = $gender;
    }

    public function getLastName() { return $this->lastName; }
    public function getFirstName() { return $this->firstName; }
    public function getCity() { return $this->city; }
    public function getGender() { return $this->gender; }
}
?>