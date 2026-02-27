<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    include_once '../service/StudentsServices.php';
    include_once '../classes/Students.php';

    $lastName = $_POST['nom'];
    $firstName = $_POST['prenom'];
    $city = $_POST['ville'];
    $gender = $_POST['sexe'];

    $studentService = new StudentsServices();
    $student = new Students(null, $lastName, $firstName, $city, $gender);

    $studentService->create($student);

    header('Content-Type: application/json');
    echo json_encode($studentService->findAllApi());
}
?>