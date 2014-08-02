<?php
// The server to connect to
$sql_server = "localhost";
// Username to connect with
$sql_user = "root";
// Password to use
$sql_pass = "";
// Database to connect to
$sql_database = "pimndb";
// Table with staff applications in it
// Attempt a connection to the above server/user/password
$sql = mysql_connect($sql_server, $sql_user, $sql_pass);
// Check that the connection was sucessful
if (!$sql)
{
	// If not, stop trying and display the error.
	die("Unable to connect to MySQL server. Error: " . mysql_error());
}
// Select the database specified above
$dbconn = mysql_select_db($sql_database);
// Check if we were able to select the database
if (!$dbconn)
{
	die("Unable to select MySQL database. Error: " . mysql_error());
}
?>