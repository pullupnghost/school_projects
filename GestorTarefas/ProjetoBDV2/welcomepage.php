<!DOCTYPE html>
<html lang="en">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome!</title>
    <link rel="stylesheet" href="./Assets/Css/default.css?v=<?= time(); ?>">
    <link rel="stylesheet" href="./Assets/Css/welcomepage.css?v=<?= time(); ?>">
</head>

<body>
    <div class="navbar">
        <a href="./Public/homepage.php">Criar</a>
        <a href="./auth.php" style="float:right;">Login</a>
    </div>

    <div class="banner">
        <div class="banner-text">
            <h1>Bem-vindo(a) ao nosso site!</h1>
            <p>Para acessar o conteúdo, faça login ou registre-se:</p>
            <a href="./auth.php" class="button">Login</a>
        </div>
    </div>

    <div class="about project">
        <h2>Sobre o Projeto</h2>
        <p>O Gestor de Tarefas é uma aplicação web desenvolvida para ajudar a gerir o seu tempo e tarefas de forma eficiente. Com ele, você pode criar tarefas, definir prioridades, agendar datas de entrega e muito mais. Tudo isto numa plataforma fácil de usar e acessível em qualquer dispositivo com acesso à internet.</p>
    </div>

    <div class="slideshow-container">
        <div class="mySlides fade">
            <img src="./Assets/img/pic1.png" alt="Imagem 1">
        </div>

        <div class="mySlides fade">
            <img src="./Assets/img/pic2.png" alt="Imagem 2">
        </div>

        <div class="mySlides fade">
            <img src="./Assets/img/pic3.png" alt="Imagem 3">
        </div>

        <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
        <a class="next" onclick="plusSlides(1)">&#10095;</a>
    </div>


    <script src="./Assets/js/slide.js"></script>


    <footer>
        <div>&copy; 2023 - Todos os direitos reservados</div>
        <div>
            <a href="https://github.com/pullupnghost" target="_blank">Github</a> |
            <a href="https://www.linkedin.com/in/joao-varela-g/" target="_blank">LinkedIn</a>
        </div>
    </footer>
</body>

</html>