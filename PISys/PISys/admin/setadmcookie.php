<?php
if ($_GET['delete'] != null)
{
	setcookie('piadmlog', '', time() - 10);
}
elseif ($_GET['user'] != null && $_GET['pass'] != null)
{
$cookie = $_GET['user'].'&'.md5($_GET['pass']);
setcookie(piadmlog, "$cookie");
}
elseif ($_GET['user'] == null && $_GET['pass'] == null)
{
	echo "Please supply a username and password element.";
}
elseif ($_GET['user'] == null)
{
	echo "Please supply a username element.";
}
elseif ($_GET['pass'] == null)
{
	echo "Please supply a password element.";
}
?>