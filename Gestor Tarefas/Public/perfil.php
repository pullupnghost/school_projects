<?php
require_once("../Client/connection.php");
session_start();

if (isset($_SESSION['user_id'])) {
    $user_id = $_SESSION['user_id'];

    // Verifica se o formulário foi enviado
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        // Recebe os valores inseridos
        $username = $_POST['username'];
        $password = $_POST['password'];
        $new_password = $_POST['new_password'];
        $confirm_password = $_POST['confirm_password'];

        $stmt = $conn->prepare("SELECT Password FROM User WHERE ID_User = ?");
        $stmt->execute([$user_id]);
        $result = $stmt->fetch();

        // Verifica se a senha atual está correta
        if (empty($result) || $result['Password'] !== $password) {
            $error_message = "Senha atual incorreta.";
        } elseif ($new_password !== $confirm_password) {
            $error_message = "A nova senha e a confirmação não coincidem.";
        } else {
            // Atualiza as informações do usuário
            $query = "UPDATE User SET Username = :username, Password = :new_password WHERE ID_User = :user_id";
            $stmt = $conn->prepare($query);
            $stmt->execute(['username' => $username, 'new_password' => $new_password, 'user_id' => $user_id]);

            if ($stmt->rowCount() > 0) {
                $success_message = "Dados alterados com sucesso!";
            } else {
                $error_message = "Erro ao salvar as alterações.";
            }
        }
    }

    // Busca as informações do usuário
    $stmt = $conn->prepare("SELECT Username FROM User WHERE ID_User = ?");
    $stmt->execute([$user_id]);
    $user = $stmt->fetch();

?>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../Assets/css/default.css?v=<?= time(); ?>">
        <link rel="stylesheet" href="../Assets/css/perfil.css?v=<?= time(); ?>">
        <title>Perfil</title>
    </head>

    <body>
        <?= isset($error_message) ? '<div class="alert">' . $error_message . '</div>' : '' ?>
        <?= isset($success_message) ? '<div class="success">' . $success_message . '</div>' : '' ?>

        <div class="navbar">
            <a href="./homepage.php">Tarefas</a>
            <a href="./tarefas.php">Criar</a>
            <a href="./perfil.php">Perfil</a>
            <a onclick="logout()" style="float:right;">Logout</a>
        </div>
        <h2>Perfil do Utilizador</h2>
        <form action="perfil.php" method="post">
            <label for="username">Nome de utilizador:</label>
            <input type="text" name="username" value="<?php echo $user['Username'] ?>" required><br><br>

            <label for="password">Senha atual:</label>
            <input type="password" name="password" required><br><br>

            <label for="new_password">Nova senha:</label>
            <input type="password" name="new_password" required><br><br>

            <label for="confirm_password">Confirmar nova senha:</label>
            <input type="password" name="confirm_password" required><br><br>

            <input type="submit" value="Salvar alterações">
        </form>

        <footer>
            <div>&copy; 2023 - Todos os direitos reservados</div>
            <div>
                <a href="https://github.com/pullupnghost" target="_blank">Github</a> |
                <a href="https://www.linkedin.com/in/joao-varela-g/" target="_blank">LinkedIn</a>
            </div>
        </footer>
        <script src="../Assets/js/confirm.js"></script>
    </body>

    </html>
<?php } else {
    header('location: ../Client/Login_error.php');
} ?>