<?php
include('dbconf.php');

// Test for Username being alphanumeric

$username = $_POST['username'];
$email = $_POST['email'];
$email2 = $_POST['email2'];
$pass = $_POST['pass'];
$pass2 = $_POST['pass2'];

if (!eregi("[^A-Za-z0-9]", $username))
{
	// Test for duplicate names
	$query="SELECT * FROM users WHERE username='$username'";
	$result=mysql_query($query);
	$num=mysql_num_rows($result);
	
	if ($num == 0)
	{
		
		// Duplicate emails, thank god for copy and paste ;P
	$query2="SELECT * FROM users WHERE username='$username'";
	$result2=mysql_query($query2);
	$num2=mysql_num_rows($result2);
	if ($num2 == 0)
	{
		//make sure that the two passwords and the two emails
		//match up.
		if ($email == $email2 && $pass == $pass2)
		{
			$confirm_code = md5(uniqid(rand()));
			
			// Stop idiots from using HTML tags
			$username = strip_tags($_POST['username']);
			$email = strip_tags($_POST['email']);
			$pass = strip_tags($_POST['pass']);
			
			// Insert data into temp database
			$query3 = "INSERT INTO TEMP SET code='$confirm_code' username='$username' email='$email' password='$pass'";
			$result3 = mysql_query($query3);
			
			if ($result3) 
			{
				$message = "Project Infinity Registration System \r\n";
				$message .= "Click on this link to activate your Project Infinity account: \r\n";
				$message .= "http://gamepod.netai.net/confirm.php?passkey=$confirm_code";
				$sentmail = mail("$email", "Your Project Infinity Account", $message);
				header("Location:thankyou.html");
				
				if ($sentmail)
				{
					echo "Please check your email address for a confirmation email :)";
				}
				else 
				{
					echo "Failed to send confirmation email, please try again";
				}
				
			}
		}
	}
	}
	else
	{
		header("Location:nameinuse.html");
	}
}
else 
{
	header("Location:invalidname.html");
}


?>