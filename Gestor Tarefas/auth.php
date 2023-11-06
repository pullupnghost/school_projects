<?php
require_once("./Client/connection.php");
session_start();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    // Verificar se o formulário de registro foi enviado
    if (isset($_POST['username']) && isset($_POST['email']) && isset($_POST['password']) && isset($_POST['password_confirm'])) {
        $username = $_POST['username'];
        $email = $_POST['email'];
        $password = $_POST['password'];
        $password_confirm = $_POST['password_confirm'];

        // Verificar se a senha e a confirmação são iguais
        if ($password !== $password_confirm) {
            $error_message = 'A senha e a confirmação de senha não coincidem.';
        } else {
            try {
                $stmt = $conn->prepare("INSERT INTO User (Username, Email, Password) VALUES (?, ?, ?)");
                $stmt->execute([$username, $email, $password]);
                $user_id = $conn->lastInsertId();
                $_SESSION['user_id'] = $user_id;
                $_SESSION['username'] = $username;
                header('Location: ./Public/homepage.php');
                exit;
            } catch (PDOException $e) {
                $error_message = 'Erro ao registrar usuário: ' . $e->getMessage();
            }
        }
    }
    // Verificar se o formulário de login foi enviado
    elseif (isset($_POST['username_mail']) && isset($_POST['password'])) {
        $username_mail = $_POST['username_mail'];
        $password = $_POST['password'];
        //Verificar se o email está no formato correto
        $is_email = filter_var($username_mail, FILTER_VALIDATE_EMAIL);
        // Se o email estiver no formato correto, verificar se o usuário existe
        if ($is_email) {
            $stmt = $conn->prepare("SELECT * FROM User WHERE Email = ? AND Password = ?");
            $stmt->execute([$username_mail, $password]);
            $user = $stmt->fetch();
        } elseif ($username_mail) {
            // Se o email não estiver no formato correto, verificar se o usuário existe
            $stmt = $conn->prepare("SELECT * FROM User WHERE Username = ? AND Password = ?");
            $stmt->execute([$username_mail, $password]);
            $user = $stmt->fetch();
        }
        // Se o usuário existir, redirecionar para a página inicial
        if ($stmt->rowCount() === 1) {
            $_SESSION["username"] = $user['Username'];
            $_SESSION["email"] = $user['Email'];
            $_SESSION["user_id"] = $user['ID_User'];
            header('Location: ./Public/homepage.php');
            exit;
        } else {
            $error_message = 'Dados inválidos ou incorretos.';
        }
    }
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./Assets/Css/auth.css?v=<?= time(); ?>">
    <link rel="stylesheet" href="./Assets/Css/default.css?v=<?= time(); ?>">
    <title>Login</title>
</head>

<body>
    <div class="background"></div>
    <div class="navbar">
        <a href="./welcomepage.php">Home</a>
    </div>
    <?= isset($error_message) ? '<div class="alert" style="top:20px;">' . $error_message . '</div>' : '' ?>
    <div class="container">
        <h2 id="titulo_form">Entrar</h2>

        <form action="auth.php" method="post" id="loginForm" style="display: block;">
            <label>Nome de Utilizador</label>
            <input type="text" name="username_mail" required>

            <label>Senha</label>
            <input type="password" name="password" required>

            <input type="submit" value="Login">
            <label></label>
            <p>Não tem uma conta? <button id="goToRegister">Registe-se aqui</button></p>
        </form>

        <form action="auth.php" method="post" id="registerForm" style="display: none;">
            <label for="username">Nome de utilizador:</label>
            <input type="text" id="username" name="username" required>

            <label for="email">E-mail:</label>
            <input type="email" id="email" name="email" required>

            <label for="password">Senha:</label>
            <input type="password" id="password" name="password" required>

            <label for="confirm-password">Confirmar senha:</label>
            <input type="password" id="confirm-password" name="password_confirm" required>

            <input type="submit" value="Registar">
            <label></label>
            <p>Já tem uma conta? <button id="goToLogin">Faça login aqui.</button></p>
        </form>
    </div>

    <footer>
        <div>&copy; 2023 - Todos os direitos reservados</div>
        <div>
            <a href="https://github.com/pullupnghost" target="_blank">Github</a> |
            <a href="https://www.linkedin.com/in/joao-varela-g/" target="_blank">LinkedIn</a>
        </div>
    </footer>
    <script src="./assets/js/auth.js"></script>
</body>

</html>