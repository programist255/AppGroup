<?php

include 'conn.php';
$IdUser = '54';
$sqltr = "SELECT First_Name, Name FROM users WHERE Id = '$IdUser'";
$result = mysqli_query($conn,$sqltr);
$row = $result->fetch_assoc();

$First_Name = $row['First_Name'];
$Name = $row['Name'];
echo $First_Name. " " . $Name;
?>
