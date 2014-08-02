<?php
	// Opens connection to MySQL server.
	$dbc = mysql_connect('mysql13.000webhost.com', 'a9402072_phptest', 'apassword');
	if (!$dbc)
	{
		die("No connection to MySQL server: " . mysql_error());
	}
	
	// Select the database
	$db_selected = mysql_select_db("a9402072_phptest", $dbc);
	if (!$db_selected)
	{
		die('Selection of database failed: ' . mysql_error());
	}
?>
