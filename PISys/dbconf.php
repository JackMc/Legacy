<?php
	// Database connection information
	$host='mysql13.000webhost.com';
	$user='a9402072_phptest';
	$pass='apassword';
	$db_name='a9402072_phptest';
	// Opens connection to MySQL server.
	$dbc = mysql_connect("$host", "$user", "$pass");
	if (!$dbc)
	{
		die('No connection to MySQL server: ' . mysql_error());
	}
	
	// Select the database
	$db_selected = mysql_select_db($db_name, $dbc);
	if (!$db_selected)
	{
		die('Selection of database failed: ' . mysql_error());
	}
?>
