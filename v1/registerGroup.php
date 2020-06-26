<?php

include 'conn.php';
$respone = array();

$NameGroup = $_POST['NameGroup'];
$PassGroup = $_POST['PasswordGroup'];
$Photo = $_POST['Photo'];
$IdUser = $_POST['Id'];

$target = "images/";
if(!file_exists($target)){
	mkdir($target, 0777,true);
}

$sqlt ="SELECT Id FROM prog_group ORDER BY Id ASC";

$res = mysqli_query($conn,$sqlt);

$Id = 0;
		
while($row = mysqli_fetch_array($res)){
	$Id = $row['Id'];
}

$target = $target ."/".rand() . "_" .$Id . "_" . time() . ".jpeg";

if(file_put_contents($target, base64_decode($Photo))) {
	echo json_encode([
		"Message" => "OK"]);
}else{
	echo json_encode([
		"Message" => "ERROR"]);
}
 $actualpath = "6/v1/$target";


$nameGroup = str_replace(" ", "_", $NameGroup) . time() . "_users";
$task_group = str_replace(" ", "_", $NameGroup) . time() . "_task";

// $task_group = 'sdf';

$sqltr = "SELECT First_Name, Name FROM users WHERE Id = '$IdUser'";
$result = mysqli_query($conn,$sqltr);
$row = $result->fetch_assoc();

$First_Name = $row['First_Name'];
$Name = $row['Name'];

//$Admin = $First_Name . " " . $Name;
// echo $First_Name. " " . $Name;

 $sql = "INSERT INTO prog_group (Id, Photo, NameGroup, PasswordGroup, admin, table_user_group, task_group) VALUES (NULL, '$actualpath', '$NameGroup', '$PassGroup', '$IdUser', '$nameGroup', '$task_group')";

 $result1 = mysqli_query($conn,$sql);
 // echo $sql . "  " . "</br>";

 $creat = "CREATE TABLE $nameGroup (Id int NOT NULL AUTO_INCREMENT,
 	Id_User int(11),
    FirstName varchar(255),
    Name varchar(255),
	PRIMARY KEY (Id))";

 $Regist = mysqli_query($conn,$creat);
//echo "CREATE TABLE $nameGroup"."</br>";

 $creat1 = "CREATE TABLE $task_group (Id int NOT NULL AUTO_INCREMENT,
    Title varchar(255),
    Texts varchar(1000),
	PRIMARY KEY (Id))";

 $Regist1 = mysqli_query($conn,$creat1);
echo "CREATE TABLE  $task_group"."</br>";
 $sqlcr = "INSERT INTO $nameGroup (Id, Id_User, FirstName, Name) VALUES (NULL, '$IdUser', '$First_Name', '$Name')";
 
$result2 = mysqli_query($conn,$sqlcr);
// echo $sqlcr . "  " . "</br>";


$sqlct = "INSERT INTO user_in_group (Id, Id_User, Table_Group_Name) VALUES (NULL, '$IdUser', '$nameGroup')";

$result3 = mysqli_query($conn,$sqlct);

// echo $sqlct . "  " . "</br>";
