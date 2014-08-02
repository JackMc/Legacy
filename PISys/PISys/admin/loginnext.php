<?php
$valcookie = $_POST['User'].'&'.md5($_POST['Pass']);
// Include the password checking file
include 'passcheck.php';
if (isCorrectCookie($valcookie))
{
	setcookie('piadmlog', $valcookie);
	header('Location:index.php');
}
elseif (!isCorrectCookie($valcookie))
{
	header('Location:login.php?error=Invalid+username/password+combination');
}
else
{
	die ("There has been a problem with the administration login system. If you could please email the following information to <a href=\"mailto:auv5@ymail.com\">auv5@ymail.com</a>, that would be wonderful: \"ERROR IN PIADMINSYS, happened at ".time().", cookie information: $valcookie\"");
}
?>