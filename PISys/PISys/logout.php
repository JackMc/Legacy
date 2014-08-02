<?php
setcookie('piadminlog', "", time() - 10);
header('Location:login.php');
?>