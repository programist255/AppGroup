<?php

include 'conn.php';
$IdUser = $_POST['Id'];
$NameGroup = $_POST['NameGroup'];

$TableGroup = '';


$sql = "SELECT table_user_group	FROM prog_group WHERE NameGroup = '$NameGroup'";
$result1 = mysqli_query($conn,$sql);
$row = $result1->fetch_assoc();

$TableGroup = $row['table_user_group'];
// echo $TableGroup;

$sql1 = "DELETE FROM user_in_group WHERE Id_User = '$IdUser'";
$result2 = mysqli_query($conn,$sql1);

$sql2 = "DELETE FROM $TableGroup WHERE Id_User = '$IdUser'";
$result3 = mysqli_query($conn,$sql2);

if($result1 && $result2 && $result3)
{
	echo "1";
}else{
	echo "0";
}
?>