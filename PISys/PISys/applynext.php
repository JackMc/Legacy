<?php
// @author Jack McCracken (auv5)
// Getting some of the things sent to us from the form in 'apply.php'
$rank = $_POST['DesRank'];
$user = $_POST['User'];
$email = $_POST['Email'];
$paragraph = $_POST['Paragraph'];
// Change all of them to lower case (except for the paragraph and rank)
$user = strtolower($user);
$email = strtolower($email);
// Declare some variables we're going to need later.
$error = "";
$query = "";
$result = "";
// Include the database configuration
include 'dbconf.php';
// Check that the fields aren't empty
if (empty($user) || empty($paragraph) || empty($email))
{
	// If they are, send them back to apply.php, with a error, saying they didn't fill out all of the fields
	header('Location:apply.php?error=You+did+not+fill+out+some+of+the+fields');
}
// Check that the fields don't contain anything that can allow the user SQL injection
elseif (strstr($user, '\'') || strstr($user, '"') || strstr($email, '\'') || strstr($email, '"') || strstr($paragraph, '\'') || strstr($paragraph, '"'))
{
	// If they are, send them back to apply.php, with a error, saying that one of the fields contains invalid chars.
	header('Location:apply.php?error=One+or+more+of+the+fields+contains+invalid+characters');
}
// If all of the checks are fine, then...
else
{
	$temp = 0;
	// Set up the query that we will use to check for duplicate usernames.
	$query = "SELECT * FROM staffapp WHERE user='$user'";
	// Do the query, and set $result to the result (true or false)
	$result = mysql_query($query);
	// Set $num to the number of rows matching the query
	$num = mysql_num_rows($result);
	// Same thing for emails :)
	$query = "SELECT * FROM staffapp WHERE email='$email'";
	$result = mysql_query($query);
	$num2 = mysql_num_rows($result);
	if ($num2 > 0)
	{
		header('Location:apply.php?error=That+email+already+has+a+pending+application.+Use+the+application+manager');
	}
	// If there are more than 0 matches...
	if ($num > 0)
	{
		// Send them back to apply.php with a error.
		header('Location:apply.php?error=That+username+already+has+a+pending+application.+Use+the+application+manager.');
	}
	// If there are 0 matches, all checks are complete, and we can begin putting the data in the database
	elseif ($num == 0 && $num2 == 0)
	{
	/*
	 * Create a code for the user, they can type this into a page, and see the status of their staff 
	 * application.
	 */
	$app_code = md5(uniqid(rand()));
	// Make sure idiots don't try to use HTML/JavaScript tags to deface/change the site.
	$email = strip_tags($email);
	$user = strip_tags($user);
	$paragraph = strip_tags($paragraph);
	// The query
	$query="INSERT INTO staffapp(user, rank, email, para, app_code) values('$user', '$rank', '$email', '$paragraph', '$app_code')";
	// A boolean of the results.
	$result=mysql_query($query);
	// If the results return false
	if (!$result)
	{
		// End the script, registration failed, and the database is most likely wrong/down/timed out
		die ("Error! Details: " . mysql_error());
	}
	// If the results return true
	else if ($result)
	{
		// Send them back to apply.php, with the sent option (see code of apply.php for more info on sent)
		header('Location:apply.php?sent='.$app_code);
		// Debug if the redirect does not work.
		echo "Data inserted! DEBUG: query is: ".$query.", user is: ".$user.", rank requested is: ".$rank." email is: ".$email." para is: ".$paragraph.", confirmation code is: ".$app_code.".";
	}
	}
}
?>