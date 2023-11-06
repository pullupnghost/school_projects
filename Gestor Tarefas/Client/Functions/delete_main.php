<?php
require_once("../connection.php");

if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST["task_id"])) {
    $task_id = $_POST["task_id"];

    $stmt = $conn->prepare("DELETE FROM Tarefa WHERE ID_Tarefa = ?");
    $stmt->execute([$task_id]);

    header("Location: ../../Public/homepage.php");
    exit;
} elseif ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST["category_id"])) {
    $task_id = $_POST["category_id"];

    // Delete the task from the database
    $stmt = $conn->prepare("DELETE FROM Categoria WHERE ID_Categoria = ?");
    $stmt->execute([$task_id]);

    // Redirect back to the task page
    header("Location: ../../Public/homepage.php");
    exit;
} else {
    // Invalid request, redirect back to the task page
    header("Location:  ../../Public/homepage.php");
    exit;
}

// Novo código adicionado
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST["category_id"])) {
    $task_id = $_POST["category_id"];

    // Delete the task from the database
    $stmt = $conn->prepare("DELETE FROM Categoria WHERE ID_Categoria = ?");
    $stmt->execute([$task_id]);

    // Redirect back to the task page
    header("Location: ../../Public/homepage.php");
    exit;
} else {
    // Invalid request, redirect back to the task page
    header("Location: ../../Public/homepage.php");
    exit;
}
?>