<!-- This file is mainly coded in HTML, as it is only for recieving the data, and does not parse it. 
This is the Project Infinity staff application page. -->
<?php
// @author Jack McCracken (Auv5)
/* Echo the title, could be done in HTML, but I decided to put it in here,
 * As I was already in PHP.
 */
echo "<div style=\"color:#FFFF00;border:1px solid black;background-color:blue\">";
echo "<h1>Project Infinity Staff Applications</h1>";
echo "</div>"; 
/* Begin checking for elements, elements are used in the following syntax:
 * apply.php?element=value for just one element, or apply.php?element1=value&element2=value
 * replacing element/element2/element1 with the desired element and value with the desired
 * value
 */
// If there is a specified error message (apply.php?error=error+text) then....
if ($_GET['error'] != null)
{
	// Check if a size is specified
	if ($_GET['size'] != null)
	{
		// Create a red box with yellow text and a black border
	echo "<div style=\"color:#FFFF00;border:1px solid black;background-color:red\">";
	// Create a header with the error, with the specified size. Replacing '+' with ' '
	echo "<h".$_GET['size'].">".str_replace('+', ' ', $_GET['error'])."</h".$_GET['size'].">";
	// End the divider.
	echo "</div>";
	}
	// If there is not a specified size, then....
	else
	{
		// Create a divider with the same style as above
	echo "<div style=\"color:#FFFF00;border:1px solid black;background-color:red\">";
	//Create a header with the error; using the default size. Replacing '+' with ' '
	echo "<h2>".str_replace('+', ' ', $_GET['error'])."</h2>";
	// End the divider
	echo "</div>";
	}
}
// Check if there is a 'sent' element, and that it is set to 'true' (apply.php?sent=true)
if ($_GET['sent'] == "true")
{
	// Create a green box with a black border and yellow text.
	echo "<div style=\"color:#FFFF00;border:1px solid black;background-color:green\">";
	// Show a size 2 header with the specified message.
	echo "<h2>Your application has been recieved. You will recieve a email within two weeks. Your tracking code is:".$_GET['sent']."</h2>";
	// End the divider
	echo "</div>";
}
?>
<!-- set the title -->
<title>Project Infinity - Apply for a staff possition</title>
<!-- Begin main divider -->
<div style="border:1px solid black">
<!-- Begin main form, sending data to 'applynext.php' -->
<form name="Reg" method="post" action="applynext.php">
<!-- Note, in bold -->
<b>Please note that all text fields in this form may not contain the chars ', " and two concurent -s (--)</b>
<!-- First field, the user's in-game name -->
<p>In-Game Username: <input type="text" maxlength="20" size="15" name="User"/> </p>
<!-- Second field, the desired rank -->
<p>
Desired rank: 
<select name="DesRank">
<option>Forums Moderator</option>
<option>Forums Administrator</option>
<option>In-Game Moderator</option>
<option>In-Game Administrator</option>
<option>Global Moderator</option>
<option>Sectional Moderator</option>
</select>
<br/>
<b>Please note: if you are applying for sectional moderator, please supply the section you are applying for in your paragraph.</b>
</p>
<!-- Field, user's email -->
<p>
Email you can be contacted at: 
<input type="text" name="Email" maxlength="30" size="15"/>
</p>
<!-- Paragraph, should add -->
<p>
Write a paragraph about why you should be a Project Infinity staff member:
<br/>
<textarea rows="10" cols="100" name="Paragraph"></textarea>
</p>
<input type="submit">
</form>
</div>