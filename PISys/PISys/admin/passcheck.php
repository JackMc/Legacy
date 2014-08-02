<?php
function isCorrectCookie($cookie)
{
	$info = spliti('&', $cookie);
	include '../dbconf.php';
	$query = "SELECT * FROM admins WHERE user='$info[0]' AND pass='$info[1]'";
	$result = mysql_query($query);
	$num = mysql_num_rows($result);
	if ($num == 1)
	{
		return true;
	}
	elseif ($num == 0)
	{
		return false;
	}
}
function getadmacc($name)
{
	$query = "SELECT * FROM admins WHERE user='$name'";
	$result = mysql_query($query);
	$adminfo = mysql_fetch_array($result, MYSQL_ASSOC);
	return $adminfo['rights'];
}

?>