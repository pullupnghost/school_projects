<?php

require_once("../Client/connection.php");
session_start();

if (isset($_SESSION['user_id'])) {
    $user_id = $_SESSION['user_id'];

    $stmt = $conn->prepare("SELECT Username FROM User WHERE ID_User = ?");
    $stmt->execute([$user_id]);
    $result = $stmt->fetch();

    $_SESSION['username'] = $result['Username'];

    if (isset($_GET['cat'])) {
        $category_id = $_GET['cat'];
        $stmt = $conn->prepare("SELECT * FROM Tarefa WHERE ID_User = ? AND ID_Categoria = ?");
        $stmt->execute([$user_id, $category_id]);
    } else {
        $stmt = $conn->prepare("SELECT * FROM Tarefa WHERE ID_User = ?");
        $stmt->execute([$user_id]);
    }
    $tarefas = $stmt->fetchAll();
?>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tarefas</title>
        <link rel="stylesheet" href="../Assets/Css/default.css?v=<?= time(); ?>">
        <link rel="stylesheet" href="../Assets/Css/homepage.css?v=<?= time(); ?>">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    </head>

    <body>
        <div class="navbar">
            <a href="./homepage.php">Tarefas</a>
            <a href="./tarefas.php">Criar</a>
            <a href="./perfil.php">Perfil</a>
            <a onclick="logout()" style="float:right;">Logout</a>
        </div>

        <h1>Tarefas do <?php echo $_SESSION['username']; ?></h1>

        <div class="categories" style="display:flex; flex-direction:row;">
            <?php
            $stmt = $conn->prepare("SELECT * FROM Categoria WHERE ID_User = ?");
            $stmt->execute([$user_id]);
            $stmt->execute();
            $categorias = $stmt->fetchAll();

            if (count($categorias) > 0) { ?>
                <?php foreach ($categorias as $categoria) {
                    // Adiciona um parâmetro na URL contendo o ID da categoria selecionada
                    $url_params = isset($_GET['cat']) ? "&cat=" . $_GET['cat'] : "";
                    $selected = isset($_GET['cat']) && $_GET['cat'] == $categoria['ID_Categoria'] ? "selected" : "";
                ?>
                    <div class="category">
                        <a href="?cat=<?php echo $categoria['ID_Categoria']; ?>" class="category <?php echo $selected; ?>" id="<?php echo $categoria['ID_Categoria']; ?>" style="border-bottom: 2px solid <?php echo $categoria['Cor']; ?>"><?php echo $categoria['Nome']; ?></a>
                        <form action="../Client/Functions/delete_main.php" method="POST">
                            <input type="hidden" name="category_id" value="<?php echo $categoria['ID_Categoria']; ?>">
                            <button onclick="deleteT_C()" type="submit" class="delete-button">
                                <i class='bx bx-trash'></i>
                            </button>
                        </form>
                    </div>
                <?php }
            } else { ?>
                <div class="no-tasks">Não existem categorias para mostrar.</div>
            <?php } ?>
        </div>

        <div class="task-container">
            <?php if (count($tarefas) > 0) { ?>
                <?php foreach ($tarefas as $tarefa) {
                    $stmt = $conn->prepare("SELECT * FROM Categoria WHERE ID_Categoria = ?");
                    $stmt->execute([$tarefa['ID_Categoria']]);
                    $categoria = $stmt->fetch();
                    $cor = $categoria['Cor'];
                ?>

                    <div class="task" style="border-bottom: 2px solid <?php echo $categoria['Cor']; ?>">
                        <h2><?php echo $tarefa['Titulo']; ?></h2>
                        <p><?php echo $tarefa['Descricao']; ?></p>
                        <div class="meta">
                            <span>Data de Inicio: <?php echo date('d/m/Y', strtotime($tarefa['Data_Criado'])); ?></span><br>
                            <span>Data de Conclusão: <?php echo date('d/m/Y', strtotime($tarefa['Data_Vencimento'])); ?></span>
                        </div>
                        <form action="../Client/Functions/delete_main.php" method="POST" class="delete-form">
                            <input type="hidden" name="task_id" value="<?php echo $tarefa['ID_Tarefa']; ?>">
                            <button onclick="deleteT_C()" type="submit" class="delete-button">
                                <i class='bx bx-trash'></i>
                            </button>
                        </form>
                    </div>
                <?php } ?>
            <?php } else { ?>
                <div class="no-tasks">Não existem tarefas para mostrar.</div>
            <?php } ?>
        </div>

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