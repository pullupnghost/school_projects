<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../Assets/Css/default.css?v=<?= time(); ?>">
    <link rel="stylesheet" href="../Assets/Css/error.css?v=<?= time(); ?>">
    <title>Área Restrita</title>
</head>

<body>
    <div class="navbar">
        <a href="../welcomepage.php">Voltar</a>
    </div>

    <div class="background"></div>
    <div class="content">
        <h1>Acesso Restrito</h1>
        <p>Você precisa estar logado para acessar esta página. Por favor, faça o login ou registre-se.</p>
        <a href="../auth.php" class="button">Fazer Login</a>
    </div>

    <footer class="footer">
        <div>&copy; 2023 - Todos os direitos reservados</div>
        <div class="footer-links">
            <a href="https://github.com/pullupnghost" target="_blank">Github</a> |
            <a href="https://www.linkedin.com/in/joao-varela-g/" target="_blank">LinkedIn</a>
        </div>
    </footer>
</body>

</html>