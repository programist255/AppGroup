<?php

include 'conn.php';
$IdUser = $_POST['Id'];
$Table_Group_Name = $_POST['NameGroup'];

$response = array();
$sqltr = "SELECT admin FROM prog_group WHERE NameGroup = '$Table_Group_Name'";

$result = mysqli_query($conn,$sqltr);
$row = $result->fetch_assoc();

$nameAdmin = $row['admin'];

$sql = "SELECT First_Name, Name FROM users WHERE Id = '$nameAdmin'";
$result = mysqli_query($conn,$sql);
$row = mysqli_fetch_array($result);
	$First_Name = $row['First_Name'];
	$Name = $row['Name'];


$NameAdmin = $First_Name . " " . $Name;
$response['0'] = $NameAdmin;


$srt = "SELECT Photo, table_user_group FROM prog_group WHERE NameGroup = '$Table_Group_Name'";

$result1 = mysqli_query($conn,$srt);

$ImageGroup = '';
$table_user_groups = '';
if($result1){
$rows = mysqli_fetch_array($result1);
	$ImageGroup = $rows['Photo'];
	$table_user_groups = $rows['table_user_group'];

}else{
	// echo "Error";
}

$response['1'] = $ImageGroup;


$sqlt ="SELECT Id FROM $table_user_groups";

$result2 = mysqli_query($conn,$sqlt);

$num_rows = mysqli_num_rows($result2);

$SizeUsers = $num_rows;
$response['2'] = $SizeUsers;

$response['3'] = $nameAdmin;
// $response['3'] = $table_user_groups;

echo json_encode($response);
header('Content-Type: application/json');

?>