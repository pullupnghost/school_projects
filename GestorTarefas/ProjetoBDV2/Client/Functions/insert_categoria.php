<?php
require_once("../connection.php");
session_start();

if (isset($_POST["nome_cat"]) && isset($_POST["cor"])) {
    $nome_cat = $_POST["nome_cat"];
    $cor = $_POST["cor"];
    $user_id = $_SESSION["user_id"];

    // Insere a categoria
    $stmt = $conn->prepare("INSERT INTO Categoria (Nome, Cor, ID_User) VALUES (?, ?, ?)");
    $stmt->execute([$nome_cat, $cor, $user_id]);

header("Location: ../../Public/tarefas.php");
$sucess_message = "Categoria adicionada com sucesso!";
        exit;
}
?>
