<?php

//Connect the Server
$link = mysqli_connect("localhost","root","");

//Check the Connection
if($link=== false){
	die("Could not connect".mysqli_connect_error());
}
//Create the Database 
$sql = "CREATE DATABASE BackEnd";
if(mysqli_query($link,$sql)){
	echo "Database Created Successfully";}
	else{
		die("ERROR: Could not Created".mysqli_error($link));
	}
	
?>
<?php
include '../BackEnd/connection.php';
//sql to create table
echo '<script type="text/javascript">alert("dbcreation started");</script>';
$sql="CREATE TABLE userregistration(
	SN INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
		PermitNo varchar(150) NOT NULL,
	    Fullname varchar(150) NOT NULL,
	    Username varchar(150) NOT NULL,
	    Password varchar(150) NOT NULL,
		Confirm varchar(150) NOT NULL,
       	RegisterDate Date
    )";

if($conn->query($sql) === TRUE)
{
	echo "Table Userregistration Created";
	
}else{
	echo "Error Creating table:".$conn->error;
}
?>


<?php
include '../BackEnd/connection.php';
//sql to create table
echo '<script type="text/javascript">alert("dbcreation started");</script>';
$sql="CREATE TABLE Shops(
	    ShopID INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
		ShopName text(200) NOT NULL,
	    Location text(200) NOT NULL,
        Director text(200) NOT NULL
    )";

if($conn->query($sql) === TRUE)
{
	echo "Table Outlet Created";	
}else{
	echo "Error Creating table:".$conn->error;
}
?>

<?php
include '../BackEnd/connection.php';
//sql to create table
echo '<script type="text/javascript">alert("dbcreation started");</script>';
$sql="CREATE TABLE Products(
	    ProductID INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
		ProductName text(200) NOT NULL,
        Price double NOT NULL,
	    CompanyName text(200) NOT NULL,
	    Category text(200) NOT NULL,
        Representative text(200) NOT NULL
    )";

if($conn->query($sql) === TRUE)
{
	echo "Table Products Created";
}else{
	echo "Error Creating table:".$conn->error;
}
?>

<?php
include '../BackEnd/connection.php';
//sql to create table
echo '<script type="text/javascript">alert("dbcreation started");</script>';
$sql="CREATE TABLE ReceiveStock(
	    ReceiveID varchar(150) NOT NULL,
        CompanyName text(200) NOT NULL,
		ProductName text(200) NOT NULL,	   
		Quantity INT(200) NOT NULL,
        Rate double NOT NULL, 		
        StockValue double NOT NULL,
        ReceiveDate Date,
		ExpiryDate Date,
        Agent text(200) NOT NULL
    )";

if($conn->query($sql) === TRUE)
{
	echo "Table ReceiveStock Created";	
}else{
	echo "Error Creating table:".$conn->error;
}
?>

<?php
include '../BackEnd/connection.php';
//sql to create table
echo '<script type="text/javascript">alert("dbcreation started");</script>';
$sql="CREATE TABLE Stock(
	    StockID varchar(150) NOT NULL,
        CompanyName text(200) NOT NULL,
		ProductName text(200) NOT NULL,	   
		Quantity INT(200) NOT NULL,
        Rate double NOT NULL, 		
        StockValue double NOT NULL,
        ExpiryDate Date,
        Agent text(200) NOT NULL
    )";

if($conn->query($sql) === TRUE)
{
	echo "Table Stock Created";	
}else{
	echo "Error Creating table:".$conn->error;
}
?>
<?php
include '../BackEnd/connection.php';
//sql to create table
echo '<script type="text/javascript">alert("dbcreation started");</script>';
$sql="CREATE TABLE SupplyStock(
	    SupplyId INT(11) NOT NULL,
		ShopName text(200) NOT NULL,
	    Director text(200) NOT NULL,
	    Productname text(200) NOT NULL,
		Quantity INT(200) NOT NULL,
        Rate double NOT NULL,
		StockValue double NOT NULL,       
		SupplyDate Date,
        Agent text(200) NOT NULL
    )";

if($conn->query($sql) === TRUE)
{
	echo "Table Supply Stock Created";	
}else{
	echo "Error Creating table:".$conn->error;
}
?>





