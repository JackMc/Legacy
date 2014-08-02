<?php
$email = $_GET['email'];
$message = "Test for emailing, if you see this, it worked :)";
$sentmail = mail($email, "Test for projectinfinity", $message);
if ($sentmail)
{
	echo "Mail sent. Please check your email :) Just for testing, email is $email";
}
else if (!$sentmail)
{
	echo "Email has not been sent. Please check the code.";
}
?>