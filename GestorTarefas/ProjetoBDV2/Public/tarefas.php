<?php
require_once("../Client/connection.php");
session_start();

if (isset($_SESSION['user_id'])) {
    $user_id = $_SESSION['user_id'];

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        // Verifica se o formulário de adição de categoria foi enviado
        $titulo = $_POST["titulo"];
        $descricao = $_POST["descricao"];
        $data_criado = date("Y-m-d H:i:s");
        $data_vencimento = $_POST["data_vencimento"];
        $id_categoria = $_POST["categoria"];

        try {
            $stmt = $conn->prepare("INSERT INTO Tarefa (ID_User, Titulo, Descricao, Data_Criado, Data_Vencimento, ID_Categoria) VALUES (?, ?, ?, ?, ?, ?)");
            $stmt->execute([$user_id, $titulo, $descricao, $data_criado, $data_vencimento, $id_categoria]);
            $success_message = 'Tarefa criada com sucesso';
        } catch (PDOException $Exception) {
            $error_message = 'Erro ao criar tarefa';
        }
    }

?>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../Assets/Css/default.css?v=<?= time(); ?>">
        <link rel="stylesheet" href="../Assets/Css/tarefa.css?v=<?= time(); ?>">
        <title>Criar Tarefa</title>
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

        <h2>Criar Tarefa</h2>
        <form action="tarefas.php" method="POST">
            <label for="titulo">Título:</label>
            <input type="text" id="titulo" name="titulo" required><br>

            <label for="descricao">Descrição:</label>
            <textarea id="descricao" name="descricao" required rows="10" cols="40" style="resize: none;"></textarea><br>

            <div style="display: flex; flex-direction: row; align-items: center; font-size:16px;">
                <div style="margin-right: 20px;">
                    <label for="data_criado" style="margin-bottom: 40px; width: 215px;">Data de Inicio:</label>
                    <label for="data_vencimento" style="margin-bottom: 10px; width: 215px;">Data de vencimento:</label>
                </div>
                <div style="display: flex; flex-direction: column;">
                    <input type="datetime-local" id="data_criado" name="data_criado" required style="margin-bottom: 20px;">
                    <input type="datetime-local" id="data_vencimento" name="data_vencimento" required>
                </div>
            </div>


            <label for="categoria">Categoria:</label><br>
            <select name="categoria" style="width: 100%;">
                <?php
                $stmt = $conn->prepare("SELECT * FROM Categoria WHERE ID_User = ?");
                $stmt->execute([$user_id]);
                $categorias = $stmt->fetchAll();

                foreach ($categorias as $row) {
                    echo "<option value='" . $row['ID_Categoria'] . "' name='categoria'>" . $row['Nome'] . "</option>";
                }
                ?>

            </select>
            <label></label>
            <div>
                <a href="#categoria-form" id="add-categoria">Adicionar Categoria</a>
            </div><br>

            <input type="submit" value="Criar Tarefa">
        </form>
        <!-- CATEGORIA -->

        <div id="add-categoria-modal" class="modal">
            <span class="close">&times;</span>
            <div class="modal-content">
                <form action="../Client/Functions/insert_categoria.php" method="POST">
                    <label for="nome_cat">Nome:</label>
                    <input type="text" id="nome_cat" name="nome_cat" required><br>

                    <label for="cor">Cor:</label>
                    <input type="color" name="cor" required style="width: 100%; height:40px;"><br>

                    <input type="submit" value="Adicionar">
                </form>
            </div>
        </div>

        <footer>
            <div>&copy; 2023 - Todos os direitos reservados</div>
            <div>
                <a href="https://github.com/pullupnghost" target="_blank">Github</a> |
                <a href="https://www.linkedin.com/in/joao-varela-g/" target="_blank">LinkedIn</a>
            </div>
        </footer>
        <script src="../Assets/Js/tarefas.js"></script>
        <script src="../Assets/js/confirm.js"></script>
    </body>

    </html>
<?php } else {
    header('location: ../Client/Login_error.php');
} ?>