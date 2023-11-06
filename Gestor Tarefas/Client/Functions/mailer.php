<?php

$email = isset($_POST['email']) ? $_POST['email'] : '';
$message = isset($_POST['message']) ? $_POST['message'] : '';

if ($email && $message) {
    $str = $email . '-' . $message . "<br/>";
    $file = fopen("test.txt","w");
    echo fwrite($file, $str);
    fclose($file);
}
?>
