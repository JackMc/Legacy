<title>Project Infinity Administration - Login</title>
<div style="color:#FFFF00;border:1px solid black;background-color:blue">
<h1>Project Infinity Administration Login</h1>
</div>
<?php 
	if ($_GET['error'] != null)
	{
	// Create a divider with the same style as above
	echo "<div style=\"color:#FFFF00;border:1px solid black;background-color:red\">";
	//Create a header with the error; using the default size. Replacing '+' with ' '
	echo "<h2>".str_replace('+', ' ', $_GET['error'])."</h2>";
	// End the divider
	echo "</div>";
	}
?>
<div style="border:1px solid black" align="center">
<form name="Login" method="post" action="loginnext.php">
<p>Username: <input name="User" type="text" maxlength="20" size="15"></p>
<p>Password: <input name="Pass" type="password" maxlength="20" size="15"></p>
<input type="submit" value="Login!">
</form>
</div>