<?php 
	$name = null;
	$home = "http://projectinfinity.net/";
	// Include the file for checking passwords and the database contifuration
	include 'passcheck.php';
	// Check if the cookie exists
	if (isset($_COOKIE['piadmlog']))
	{
		// Set the cookie as the var '$loccookie'
		$loccookie = $_COOKIE['piadmlog'];
		// Make sure loccookie is a valid cookie, and we don't have some sort of theif on our hands :O
		if (isCorrectCookie($loccookie))
		{
			// Split the name and the MD5 encrypted password into a array
			$info = spliti('&', $loccookie);
			$name = $info[0];
			$rights = getadmacc($name);
			echo "<title>Project Infinity Administration - Index</title>";
			echo "<div style=\"color:#FFFF00;border:1px solid black;background-color:blue\">";
			echo "<h1>Project Infinity Administration Index</h1>";
			echo "</div>";
			echo "<div align=\"right\">Welcome $name!,<br/>Would you like to <a href=\"$home/pisys/admin/logout.php\">Log out</a>?</div>";
			if ($rights >= 3)
			{
				echo "<div align=\"center\"><a href=\"$home/pisys/admapp.php\">Approve/deny a staff application</a>";
			}
			
		}
		elseif (!isCorrectCookie($loccookie))
		{
			header('Location:login.php?error=Cookie+corrupt,+please+try+and+log+in+again');
		}
	}
	else if (!isset($_COOKIE['piadmlog']))
	{
		header('Location:login.php');
	}
?>

