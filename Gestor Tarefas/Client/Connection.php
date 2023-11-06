<?php

$dsn = 'mysql:host=localhost;dbname=gestortarefasdb';
$usernamedb = 'root';
$passworddb = '';

try {
    $conn = new PDO($dsn, $usernamedb, $passworddb);
} catch (PDOException $e) {
    die('Erro ao conectar-se à base de dados: ') . $e->getMessage();
}

isset($error_message) ? '<div class="alert">' . $error_message . '</div>' : '';

?>