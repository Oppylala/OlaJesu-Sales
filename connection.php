<?php
$servername ="localhost";
$username = "root";
$password = "";
$dbname = "BackEnd";

//connecting the server
$conn = mysqli_connect($servername, $username, $password, $dbname);

//Test the connection
if($conn->connect_error){
	echo"Error";
}

?>