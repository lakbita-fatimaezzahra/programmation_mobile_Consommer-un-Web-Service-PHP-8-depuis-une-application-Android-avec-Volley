<?php
 include_once '../service/StudentsServices.php';

$studentService = new StudentsServices();

header('Content-Type: application/json');
echo json_encode($studentService->findAllApi());
?>